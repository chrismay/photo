package uk.co.blackpepper.photocomp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableVoteForPhotoCommand;
import uk.co.blackpepper.photocomp.web.view.ImmutableUpdatedAggregateResponse;
import uk.co.blackpepper.photocomp.web.view.UpdatedAggregateResponse;

@RestController
public class VoteController
{

    private final CommandDispatcher dispatcher;

    @Autowired
    public VoteController(CommandDispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    @RequestMapping(value = "/photo/{photoId}/votes", method = RequestMethod.POST)
    public UpdatedAggregateResponse vote(@PathVariable String photoId, @RequestParam String user)
    {
        dispatcher.dispatchCommand(ImmutableVoteForPhotoCommand.builder()
            .submissionId(photoId)
            .userName(user)
            .build());
        return ImmutableUpdatedAggregateResponse.builder().id(photoId).build();
    }
}
