package uk.co.blackpepper.photocomp.submission;

import uk.co.blackpepper.photocomp.submission.api.commands.RetractPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.VoteForPhotoCommand;

public interface Submission
{

    void retract(RetractPhotoCommand command);

    boolean isRetracted();

    void vote(VoteForPhotoCommand command);
}
