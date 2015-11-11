package uk.co.blackpepper.photocomp.submission;

import uk.co.blackpepper.photocomp.submission.api.commands.RetractPhotoCommand;

public interface Submission
{

    void retract(RetractPhotoCommand command);

    boolean isRetracted();
}
