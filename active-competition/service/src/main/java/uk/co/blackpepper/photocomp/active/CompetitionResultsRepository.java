package uk.co.blackpepper.photocomp.active;

import java.util.stream.Collectors;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;

public class CompetitionResultsRepository implements CompetitionResultsQuery
{
    private CompetitionResults results;

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        results = ImmutableCompetitionResults.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .build();
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        if (event.getCompetitionId().equals(results.competitionId()))
        {
            results = ImmutableCompetitionResults.builder()
                .from(results)
                .addPhotos(ImmutablePhotoVotes.builder()
                    .id(event.getSubmissionId())
                    .caption(event.getCaption())
                    .submitter(event.getUploadedBy())
                    .totalVotes(0)
                    .build())
                .build();
        }
    }

    @EventHandler
    public void onVoteCast(final PhotoVotedForEvent event)
    {
        Iterable<? extends PhotoVotes> otherPhotos =
            results.photos().stream()
                .filter(p -> !p.id().equals(event.getPhotoId()))
                .collect(Collectors.<PhotoVotes>toList());

        PhotoVotes votedFor = results.photos().stream()
            .filter(p -> p.id().equals(event.getPhotoId()))
            .findFirst().get();


        results = ImmutableCompetitionResults.builder()
            .from(results)
            .photos(otherPhotos)
            .addPhotos(ImmutablePhotoVotes.builder()
                .from(votedFor)
                .addVoters(event.getVoterId())
                .totalVotes(votedFor.totalVotes() + 1)
                .build()
            )
            .build();
    }

    @Override
    public CompetitionResults getCurrentCompetitionResults()
    {
        return results;
    }
}
