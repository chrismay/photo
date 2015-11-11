package uk.co.blackpepper.photocomp.competition;

import org.axonframework.commandhandling.annotation.CommandHandler;

import uk.co.blackpepper.photocomp.competition.api.commands.CreateCompetitionCommand;

public class CreateCompetition
{

    private final CompetitionRepository repository;

    public CreateCompetition(CompetitionRepository repository)
    {
        this.repository = repository;
    }

    @CommandHandler
    public void handleCreateCompetitionCommand(CreateCompetitionCommand command)
    {
        repository.add(command);
    }
}
