package uk.co.blackpepper.photocomp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.active.CompetitionResults;
import uk.co.blackpepper.photocomp.active.CompetitionResultsQuery;

@RestController
public class CompetitionResultsController
{
    private final CompetitionResultsQuery query;

    @Autowired
    public CompetitionResultsController(CompetitionResultsQuery query)
    {
        this.query = query;
    }

    @RequestMapping(value = "/competition/results")
    @ResponseBody
    public CompetitionResults show()
    {
        return query.getCurrentCompetitionResults();
    }
}
