package uk.co.blackpepper.photocomp.submittedphoto;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.query.persistence.MapPersistenceService;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;
import uk.co.blackpepper.photocomp.submission.api.events.SubmissionRetractedEvent;

public class SubmittedPhotoRepository implements SubmittedPhotoQuery
{
    private static final String PHOTO_MAP = "photos";
    private static final String PHOTO_METADATA_MAP = "photo-metadata";
    private final MapPersistenceService mapStore;

    public SubmittedPhotoRepository(MapPersistenceService mapStore)
    {
        this.mapStore = mapStore;
    }


    public SubmittedPhoto getByID(String id)
    {
        return getPhoto(id);
    }

    @Override
    public SubmittedPhotoMetadata getMetadataById(String id)
    {
        return getMetadata(id);
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        storePhoto(event.getSubmissionId(), ImmutableSubmittedPhoto.builder()
            .content(event.getPhotoContent())
            .build());

        storeMetadata(event.getSubmissionId(), ImmutableSubmittedPhotoMetadata.builder()
            .caption(event.getCaption())
            .build());
    }

    @EventHandler
    public void onVoteCast(PhotoVotedForEvent vote)
    {
        SubmittedPhotoMetadata current = getMetadata(vote.getPhotoId());
        storeMetadata(vote.getPhotoId(), ImmutableSubmittedPhotoMetadata.builder()
            .from(current)
            .addVoters(vote.getVoterId())
            .build());
    }

    @EventHandler
    public void onPhotoRetracted(SubmissionRetractedEvent event)
    {
        mapStore.remove(PHOTO_MAP, event.getSubmissionId());
    }

    private SubmittedPhotoMetadata getMetadata(String id)
    {
        return mapStore.get(PHOTO_METADATA_MAP, id, SubmittedPhotoMetadata.class);
    }

    private void storeMetadata(String id, SubmittedPhotoMetadata meta)
    {
        mapStore.put(PHOTO_METADATA_MAP, id, meta);
    }

    private SubmittedPhoto getPhoto(String id)
    {
        return mapStore.get(PHOTO_MAP, id, SubmittedPhoto.class);
    }

    private void storePhoto(String id, SubmittedPhoto store)
    {
        mapStore.put(PHOTO_MAP, id, store);
    }
}
