package uk.co.blackpepper.photocomp.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.active.CompetitionQuery;
import uk.co.blackpepper.photocomp.active.CompetitionResults;
import uk.co.blackpepper.photocomp.active.CompetitionResultsQuery;
import uk.co.blackpepper.photocomp.active.PhotoCompetition;

@RestController
public class CompetitionResultsController
{
    private final CompetitionResultsQuery query;

    private final CompetitionQuery competitions;

    @Autowired
    public CompetitionResultsController(CompetitionResultsQuery query, CompetitionQuery competitions)
    {
        this.query = query;
        this.competitions = competitions;
    }

    @RequestMapping(value = "/competition/results")
    @ResponseBody
    public CompetitionResults show()
    {
        return query.getCurrentCompetitionResults();
    }

    @RequestMapping(value = "/competitions")
    @ResponseBody
    public Collection<PhotoCompetition> list()
    {
        return competitions.getAll();
    }
}
