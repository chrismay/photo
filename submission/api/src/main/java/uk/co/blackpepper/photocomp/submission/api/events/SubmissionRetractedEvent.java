package uk.co.blackpepper.photocomp.submission.api.events;

import org.immutables.value.Value;

@Value.Immutable
public interface SubmissionRetractedEvent
{
    String getSubmissionId();
}
