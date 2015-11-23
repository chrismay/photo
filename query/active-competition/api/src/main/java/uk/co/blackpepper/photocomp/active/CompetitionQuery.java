package uk.co.blackpepper.photocomp.active;

import java.util.Collection;

public interface CompetitionQuery
{
    PhotoCompetition getCurrent();

    Collection<PhotoCompetition> getAll();
}
