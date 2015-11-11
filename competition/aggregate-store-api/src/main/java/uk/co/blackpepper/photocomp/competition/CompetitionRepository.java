package uk.co.blackpepper.photocomp.competition;

import uk.co.blackpepper.photocomp.competition.api.commands.CreateCompetitionCommand;

public interface CompetitionRepository
{
    Competition load(String id);

    void add(CreateCompetitionCommand createCompetitionCommand);
}
