package uk.co.blackpepper.photocomp.competition.api.events;

import org.immutables.value.Value;

@Value.Immutable
public interface CompetitionCreatedEvent
{

    String getCompetitionId();
    String getTopic();
}
