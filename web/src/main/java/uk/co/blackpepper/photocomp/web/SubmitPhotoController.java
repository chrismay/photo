package uk.co.blackpepper.photocomp.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableRetractPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableSubmitPhotoCommand;

@RestController
public class SubmitPhotoController
{


    private final CommandDispatcher dispatcher;

    @Autowired
    public SubmitPhotoController(CommandDispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    @RequestMapping("/photo")
    public String index()
    {
        String submissionId = UUID.randomUUID().toString();

        Object submit = ImmutableSubmitPhotoCommand.builder()
            .submissionId(submissionId)
            .caption("test")
            .photoContent("xxx".getBytes())
            .uploadedBy("test")
            .build();


        dispatcher.dispatchCommand(submit);

        Object retract = ImmutableRetractPhotoCommand.builder()
            .submissionId(submissionId)
            .build();

        dispatcher.dispatchCommand(retract);

        return "Greetings from Spring Boot! - created and retracted " + submissionId;
    }
}
