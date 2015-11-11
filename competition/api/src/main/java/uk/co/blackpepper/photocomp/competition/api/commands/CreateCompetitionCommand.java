package uk.co.blackpepper.photocomp.competition.api.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.immutables.value.Value;

@Value.Immutable
public interface CreateCompetitionCommand
{
    @TargetAggregateIdentifier
    String getTopicId();

    String getTopic();
}
