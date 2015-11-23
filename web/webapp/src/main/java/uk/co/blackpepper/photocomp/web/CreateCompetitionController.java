package uk.co.blackpepper.photocomp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.competition.api.commands.CreateCompetitionCommand;
import uk.co.blackpepper.photocomp.competition.api.commands.ImmutableCreateCompetitionCommand;
import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;
import uk.co.blackpepper.photocomp.web.view.CreatedAggregateResponse;
import uk.co.blackpepper.photocomp.web.view.ImmutableCreatedAggregateResponse;

import static uk.co.blackpepper.photocomp.service.api.AggregateId.newAggregateId;

@RestController
public class CreateCompetitionController
{
    private final CommandDispatcher dispatcher;

    @Autowired
    public CreateCompetitionController(CommandDispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    @RequestMapping(value = "/competition", method = RequestMethod.POST)
    public CreatedAggregateResponse create(@RequestParam String topic)
    {
        CreateCompetitionCommand command = ImmutableCreateCompetitionCommand.builder()
            .topicId(newAggregateId())
            .topic(topic)
            .build();
        dispatcher.dispatchCommand(command);
        return ImmutableCreatedAggregateResponse.builder().id(command.getTopicId()).build();
    }
}
