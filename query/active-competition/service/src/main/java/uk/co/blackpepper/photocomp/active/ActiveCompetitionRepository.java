package uk.co.blackpepper.photocomp.active;

import java.util.Collections;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.query.persistence.MapPersistenceService;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;

public class ActiveCompetitionRepository implements ActiveCompetitionQuery
{
    private final String MAP_NAME = "ActiveCompetition";
    private final String KEY = "current";
    private final MapPersistenceService mapStore;

    public ActiveCompetitionRepository(MapPersistenceService mapStore)
    {
        this.mapStore = mapStore;
    }

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        mapStore.put(MAP_NAME, KEY, ImmutableActiveCompetition.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .usersVoted(Collections.<String>emptySet())
            .build());
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        ActiveCompetition currentActive = mapStore.get(MAP_NAME, KEY, ActiveCompetition.class);
        if (event.getCompetitionId().equals(currentActive.competitionId()))
        {
            mapStore.put(MAP_NAME, KEY, ImmutableActiveCompetition.builder()
                .from(currentActive)
                .addPhotos(ImmutableActivePhoto.builder()
                    .id(event.getSubmissionId())
                    .caption(event.getCaption())
                    .build())
                .build());
        }
    }

    @EventHandler
    public void onVoteCast(PhotoVotedForEvent event)
    {
        ActiveCompetition currentActive = mapStore.get(MAP_NAME, KEY, ActiveCompetition.class);

        mapStore.put(MAP_NAME, KEY, ImmutableActiveCompetition.builder()
            .from(currentActive)
            .addUsersVoted(event.getVoterId())
            .build());
    }

    public ActiveCompetition getCurrent()
    {
        return mapStore.get(MAP_NAME, KEY, ActiveCompetition.class);
    }
}
