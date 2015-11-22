package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.active.ActiveCompetitionQuery;
import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;

public class SubmitPhoto
{

    private final SubmissionRepository repo;
    private final ActiveCompetitionQuery competitionQuery;

    public SubmitPhoto(SubmissionRepository repo, ActiveCompetitionQuery competitionQuery)
    {
        this.repo = repo;
        this.competitionQuery = competitionQuery;
    }

    @CommandHandler
    public void handleSubmitPhotoCommand(SubmitPhotoCommand command)
    {

        if (!competitionQuery.getCurrent().competitionId().equals(command.getCompetitionId()))
        {
            throw new RuntimeException("Tried to add a photo to an inactive competition");
        }
        repo.add(command);
    }
}
