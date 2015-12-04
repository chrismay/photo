package uk.co.blackpepper.photocomp.submission.api.events;

import org.immutables.value.Value;

@Value.Immutable
public interface PhotoSubmittedEvent
{
    byte[] getPhotoContent();

    String getUploadedBy();

    String getCaption();

    String getSubmissionId();

    String getCompetitionId();
}
