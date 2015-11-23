package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.active.CompetitionQuery;
import uk.co.blackpepper.photocomp.active.PhotoCompetition;
import uk.co.blackpepper.photocomp.submission.api.commands.VoteForPhotoCommand;

public class VoteForPhoto
{


    private final SubmissionRepository repo;
    private final CompetitionQuery competitions;

    public VoteForPhoto(SubmissionRepository repo, CompetitionQuery competitions)
    {
        this.repo = repo;
        this.competitions = competitions;
    }

    @CommandHandler
    public void handleVoteForPhoto(VoteForPhotoCommand command)
    {
        Submission sub = repo.load(command.getSubmissionId());
        PhotoCompetition current = competitions.getCurrent();
        if (current.usersVoted().contains(command.getUserName()))
        {
            throw new RuntimeException(
                "User " + command.getUserName() + " has already voted in the current competition");
        }
        sub.vote(command);
    }
}
