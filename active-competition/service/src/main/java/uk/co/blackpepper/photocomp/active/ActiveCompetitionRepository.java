package uk.co.blackpepper.photocomp.active;

import java.util.Collections;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;

public class ActiveCompetitionRepository implements ActiveCompetitionQuery
{
    private ActiveCompetition currentActive;

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        currentActive = ImmutableActiveCompetition.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .usersVoted(Collections.<String>emptySet())
            .build();
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        if (event.getCompetitionId().equals(currentActive.competitionId()))
        {
            currentActive = ImmutableActiveCompetition.builder()
                .from(currentActive)
                .addPhotos(ImmutableActivePhoto.builder()
                    .id(event.getSubmissionId())
                    .caption(event.getCaption())
                    .build())
                .build();
        }
    }

    @EventHandler
    public void onVoteCast(PhotoVotedForEvent event)
    {
        currentActive = ImmutableActiveCompetition.builder()
            .from(currentActive)
            .addUsersVoted(event.getVoterId())
            .build();
    }

    public ActiveCompetition getCurrent()
    {
        return currentActive;
    }
}
