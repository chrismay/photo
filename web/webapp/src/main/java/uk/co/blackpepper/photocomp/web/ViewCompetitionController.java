package uk.co.blackpepper.photocomp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.active.ActiveCompetition;
import uk.co.blackpepper.photocomp.active.ActiveCompetitionQuery;

@RestController
public class ViewCompetitionController
{
    private final ActiveCompetitionQuery query;

    @Autowired
    public ViewCompetitionController(ActiveCompetitionQuery query)
    {
        this.query = query;
    }

    @RequestMapping(value = "/competition/current")
    @ResponseBody
    public ActiveCompetition show()
    {
        return query.getCurrent();
    }
}
