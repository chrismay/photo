package uk.co.blackpepper.photocomp.active;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;

public class ActiveCompetitionRepository implements ActiveCompetitionQuery
{
    private ActiveCompetition currentActive;

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        currentActive = ImmutableActiveCompetition.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .build();
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        if (event.getCompetitionId().equals(currentActive.competitionId()))
        {
            currentActive = ImmutableActiveCompetition.builder()
                .competitionId(currentActive.competitionId())
                .topic(currentActive.topic())
                .addAllPhotoIds(currentActive.photoIds())
                .addPhotoIds(event.getSubmissionId())
                .build();
        }
    }

    public ActiveCompetition getCurrent()
    {
        return currentActive;
    }
}
