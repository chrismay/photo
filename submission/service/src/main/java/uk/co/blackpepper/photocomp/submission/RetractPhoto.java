package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.submission.api.commands.RetractPhotoCommand;

public class RetractPhoto
{

    private final SubmissionRepository repo;

    public RetractPhoto(SubmissionRepository repo)
    {
        this.repo = repo;
    }

    @CommandHandler
    public void handleRetractPhotoCommand(RetractPhotoCommand command)
    {
        Submission sub = repo.load(command.getSubmissionId());
        if (sub.isRetracted())
        {
            throw new RuntimeException("Can't retract this photo - already retracted");
        }
        sub.retract(command);
    }
}
