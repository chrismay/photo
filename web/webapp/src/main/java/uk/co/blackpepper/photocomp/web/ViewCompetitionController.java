package uk.co.blackpepper.photocomp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.active.CompetitionQuery;
import uk.co.blackpepper.photocomp.active.PhotoCompetition;

@RestController
public class ViewCompetitionController
{
    private final CompetitionQuery query;

    @Autowired
    public ViewCompetitionController(CompetitionQuery query)
    {
        this.query = query;
    }

    @RequestMapping(value = "/competition/current")
    @ResponseBody
    public PhotoCompetition show()
    {
        return query.getCurrent();
    }
}
