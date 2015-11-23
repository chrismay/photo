package uk.co.blackpepper.photocomp.active;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.query.persistence.MapPersistenceService;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;

public class CompetitionRepository implements CompetitionQuery
{
    private final String ALL_COMPETITIONS_MAP_NAME = "AllCompetitions";
    private final String ACTIVE_MAP_NAME = "ActiveCompetition";
    private final String CURRENT_KEY = "current";
    private final MapPersistenceService mapStore;

    public CompetitionRepository(MapPersistenceService mapStore)
    {
        this.mapStore = mapStore;
    }

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {

        PhotoCompetition previousActive = getCurrentActive();

        PhotoCompetition newCompetition = ImmutablePhotoCompetition.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .active(true)
            .usersVoted(Collections.<String>emptySet())
            .build();
        mapStore.put(ACTIVE_MAP_NAME, CURRENT_KEY, newCompetition);
        mapStore.put(ALL_COMPETITIONS_MAP_NAME, newCompetition.competitionId(), newCompetition);
        // Mark the previously-active competition as no longer active
        mapStore.put(ALL_COMPETITIONS_MAP_NAME, previousActive.competitionId(),
            ImmutablePhotoCompetition.builder()
                .from(previousActive)
                .active(false)
                .build());
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        PhotoCompetition currentActive = getCurrentActive();
        PhotoCompetition updated = ImmutablePhotoCompetition.builder()
            .from(currentActive)
            .addPhotos(ImmutableActivePhoto.builder()
                .id(event.getSubmissionId())
                .caption(event.getCaption())
                .build())
            .build();

        if (event.getCompetitionId().equals(currentActive.competitionId()))
        {
            mapStore.put(ACTIVE_MAP_NAME, CURRENT_KEY, updated);
        }
        mapStore.put(ALL_COMPETITIONS_MAP_NAME, updated.competitionId(), updated);
    }

    private PhotoCompetition getCurrentActive()
    {
        return mapStore.get(ACTIVE_MAP_NAME, CURRENT_KEY, PhotoCompetition.class);
    }

    @EventHandler
    public void onVoteCast(PhotoVotedForEvent event)
    {
        PhotoCompetition currentActive = getCurrentActive();
        PhotoCompetition updated = ImmutablePhotoCompetition.builder()
            .from(currentActive)
            .addUsersVoted(event.getVoterId())
            .build();
        mapStore.put(ACTIVE_MAP_NAME, CURRENT_KEY, updated);
        mapStore.put(ALL_COMPETITIONS_MAP_NAME, updated.competitionId(), updated);
    }

    public PhotoCompetition getCurrent()
    {
        return getCurrentActive();
    }

    @Override
    public Collection<PhotoCompetition> getAll()
    {
        Map<String, PhotoCompetition> allComps = mapStore.getAll(ALL_COMPETITIONS_MAP_NAME, PhotoCompetition.class);
        return allComps.values();
    }
}
