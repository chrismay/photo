package uk.co.blackpepper.photocomp.active;

import java.util.stream.Collectors;

import org.axonframework.eventhandling.annotation.EventHandler;

import uk.co.blackpepper.photocomp.competition.api.events.CompetitionCreatedEvent;
import uk.co.blackpepper.photocomp.query.persistence.MapPersistenceService;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.PhotoVotedForEvent;

public class CompetitionResultsRepository implements CompetitionResultsQuery
{
    private final MapPersistenceService mapStore;
    private final String MAP_NAME = "CompetitionResults";
    private final String KEY = "current";
    
    public CompetitionResultsRepository(MapPersistenceService mapStore)
    {
        this.mapStore = mapStore;
    }

    @EventHandler
    public void onCompetitionCreated(CompetitionCreatedEvent event)
    {
        mapStore.put(MAP_NAME, KEY, ImmutableCompetitionResults.builder()
            .competitionId(event.getCompetitionId())
            .topic(event.getTopic())
            .build());
    }

    @EventHandler
    public void onPhotoSubmitted(PhotoSubmittedEvent event)
    {
        final CompetitionResults results = getCurrent();
        if (event.getCompetitionId().equals(results.competitionId()))
        {
            replaceCurrent(ImmutableCompetitionResults.builder()
                .from(results)
                .addPhotos(ImmutablePhotoVotes.builder()
                    .id(event.getSubmissionId())
                    .caption(event.getCaption())
                    .submitter(event.getUploadedBy())
                    .totalVotes(0)
                    .build())
                .build());
        }
    }

    @EventHandler
    public void onVoteCast(final PhotoVotedForEvent event)
    {
        final CompetitionResults results = getCurrent();
        Iterable<? extends PhotoVotes> otherPhotos =
            getCurrent().photos().stream()
                .filter(p -> !p.id().equals(event.getPhotoId()))
                .collect(Collectors.<PhotoVotes>toList());

        PhotoVotes votedFor = results.photos().stream()
            .filter(p -> p.id().equals(event.getPhotoId()))
            .findFirst().get();


        replaceCurrent(ImmutableCompetitionResults.builder()
            .from(results)
            .photos(otherPhotos)
            .addPhotos(ImmutablePhotoVotes.builder()
                .from(votedFor)
                .addVoters(event.getVoterId())
                .totalVotes(votedFor.totalVotes() + 1)
                .build()
            )
            .build());
    }

    private void replaceCurrent(CompetitionResults results)
    {
        mapStore.put(MAP_NAME, KEY, results);
    }

    private CompetitionResults getCurrent()
    {
        return mapStore.get(MAP_NAME, KEY, CompetitionResults.class);
    }

    @Override
    public CompetitionResults getCurrentCompetitionResults()
    {
        return getCurrent();
    }
}
