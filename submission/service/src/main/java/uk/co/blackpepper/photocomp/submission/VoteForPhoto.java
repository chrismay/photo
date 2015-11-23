package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.active.ActiveCompetition;
import uk.co.blackpepper.photocomp.active.ActiveCompetitionQuery;
import uk.co.blackpepper.photocomp.submission.api.commands.VoteForPhotoCommand;

public class VoteForPhoto
{


    private final SubmissionRepository repo;
    private final ActiveCompetitionQuery competitions;

    public VoteForPhoto(SubmissionRepository repo, ActiveCompetitionQuery competitions)
    {
        this.repo = repo;
        this.competitions = competitions;
    }

    @CommandHandler
    public void handleVoteForPhoto(VoteForPhotoCommand command)
    {
        Submission sub = repo.load(command.getSubmissionId());
        ActiveCompetition current = competitions.getCurrent();
        if (current.usersVoted().contains(command.getUserName()))
        {
            throw new RuntimeException(
                "User " + command.getUserName() + " has already voted in the current competition");
        }
        sub.vote(command);
    }
}
