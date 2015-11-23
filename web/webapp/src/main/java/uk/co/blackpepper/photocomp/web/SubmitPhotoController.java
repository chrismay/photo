package uk.co.blackpepper.photocomp.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableSubmitPhotoCommand;
import uk.co.blackpepper.photocomp.web.view.CreatedAggregateResponse;
import uk.co.blackpepper.photocomp.web.view.ImmutableCreatedAggregateResponse;

import static uk.co.blackpepper.photocomp.service.api.AggregateId.newAggregateId;

@RestController
public class SubmitPhotoController
{

    private final CommandDispatcher dispatcher;

    @Autowired
    public SubmitPhotoController(CommandDispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    @RequestMapping(value = "/competition/{competitionId}/photos", method = RequestMethod.POST)
    public CreatedAggregateResponse add(
        @PathVariable String competitionId,
        @RequestParam MultipartFile file,
        @RequestParam String caption) throws IOException
    {
        String submissionId = newAggregateId();

        Object submit = ImmutableSubmitPhotoCommand.builder()
            .submissionId(submissionId)
            .caption(caption)
            .photoContent(file.getBytes())
            .uploadedBy("test")
            .competitionId(competitionId)
            .build();


        dispatcher.dispatchCommand(submit);

        return ImmutableCreatedAggregateResponse.builder().id(submissionId).build();
    }
}
