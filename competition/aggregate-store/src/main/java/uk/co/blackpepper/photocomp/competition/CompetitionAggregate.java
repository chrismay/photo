package uk.co.blackpepper.photocomp.competition;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import uk.co.blackpepper.photocomp.competition.api.commands.CreateCompetitionCommand;
import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.competition.api.events.ImmutableCompetitionCreatedEvent;

public class CompetitionAggregate extends AbstractAnnotatedAggregateRoot implements Competition
{

    @AggregateIdentifier
    private String id;

    CompetitionAggregate()
    {
        // for axon
    }

    public CompetitionAggregate(CreateCompetitionCommand command)
    {
        apply(ImmutableCompetitionCreatedEvent.builder()
            .competitionId(command.getTopicId())
            .topic(command.getTopic())
            .build());
    }

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        this.id = event.getCompetitionId();
    }
}
