package uk.co.blackpepper.photocomp.submission;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import uk.co.blackpepper.photocomp.submission.api.commands.RetractPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.events.ImmutablePhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.ImmutableSubmissionRetractedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.SubmissionRetractedEvent;

public class SubmissionAggregate extends AbstractAnnotatedAggregateRoot implements Submission
{
    @AggregateIdentifier
    private String id;
    private boolean retracted = false;

    SubmissionAggregate()
    {
        // for axon
    }

    public SubmissionAggregate(SubmitPhotoCommand command)
    {
        PhotoSubmittedEvent creation = ImmutablePhotoSubmittedEvent.builder()
            .caption(command.getCaption())
            .photoContent(command.getPhotoContent())
            .uploadedBy(command.getUploadedBy())
            .submissionId(command.getSubmissionId())
            .competitionId(command.getCompetitionId())
            .build();

        apply(creation);
    }


    @Override
    public void retract(RetractPhotoCommand command)
    {
        apply(ImmutableSubmissionRetractedEvent.builder()
            .submissionId(command.getSubmissionId()).build());
    }

    @Override
    public boolean isRetracted()
    {
        return retracted;
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        this.id = event.getSubmissionId();
    }

    @EventHandler
    public void onSubmissionRetracted(SubmissionRetractedEvent event)
    {
        this.retracted = true;
    }
}
