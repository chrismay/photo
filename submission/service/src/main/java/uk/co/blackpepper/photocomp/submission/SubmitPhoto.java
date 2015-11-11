package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;

public class SubmitPhoto
{

    private final SubmissionRepository repo;

    public SubmitPhoto(SubmissionRepository repo)
    {
        this.repo = repo;
    }

    @CommandHandler
    public void handleSubmitPhotoCommand(SubmitPhotoCommand command)
    {
        repo.add(command);
    }
}
