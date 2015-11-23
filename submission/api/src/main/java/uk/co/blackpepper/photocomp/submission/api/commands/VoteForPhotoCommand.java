package uk.co.blackpepper.photocomp.submission.api.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.immutables.value.Value;

@Value.Immutable
public interface VoteForPhotoCommand
{
    @TargetAggregateIdentifier
    String getSubmissionId();

    String getUserName();
}
