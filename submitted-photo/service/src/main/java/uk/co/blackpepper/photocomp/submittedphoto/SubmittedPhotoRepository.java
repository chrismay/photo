package uk.co.blackpepper.photocomp.submittedphoto;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;
import uk.co.blackpepper.photocomp.submission.api.events.SubmissionRetractedEvent;

public class SubmittedPhotoRepository implements SubmittedPhotoQuery
{
    private Map<String, SubmittedPhoto> photos = new HashMap<>();
    private Map<String, SubmittedPhotoMetadata> metadata = new HashMap<>();

    public SubmittedPhoto getByID(String id)
    {
        return photos.get(id);
    }

    @Override
    public SubmittedPhotoMetadata getMetadataById(String id)
    {
        return metadata.get(id);
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        photos.put(event.getSubmissionId(), ImmutableSubmittedPhoto.builder()
            .content(event.getPhotoContent())
            .build());
        metadata.put(event.getSubmissionId(), ImmutableSubmittedPhotoMetadata.builder()
            .caption(event.getCaption())
            .build());
    }

    @EventHandler
    public void onVoteCast(PhotoVotedForEvent vote)
    {
        SubmittedPhotoMetadata current = metadata.get(vote.getPhotoId());
        metadata.put(vote.getPhotoId(), ImmutableSubmittedPhotoMetadata.builder()
            .from(current)
            .addVoters(vote.getVoterId())
            .build());
    }

    @EventHandler
    public void onPhotoRetracted(SubmissionRetractedEvent event)
    {
        photos.remove(event.getSubmissionId());
    }
}
