package uk.co.blackpepper.photocomp.submission;

import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;

public interface SubmissionRepository
{
    Submission load(String id);

    void add(SubmitPhotoCommand submission);
}
