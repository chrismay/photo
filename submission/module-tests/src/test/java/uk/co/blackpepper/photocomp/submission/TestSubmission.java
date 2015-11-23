package uk.co.blackpepper.photocomp.submission;

import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.repository.Repository;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.co.blackpepper.photocomp.active.CompetitionQuery;
import uk.co.blackpepper.photocomp.active.ImmutablePhotoCompetition;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableRetractPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.ImmutableSubmitPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.RetractPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;
import uk.co.blackpepper.photocomp.submission.api.events.ImmutablePhotoSubmittedEvent;
import uk.co.blackpepper.photocomp.submission.api.events.ImmutableSubmissionRetractedEvent;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestSubmission
{

    private FixtureConfiguration<SubmissionAggregate> fixture;

    @Mock
    private CompetitionQuery competitionQuery;

    @Before
    public void setUp() throws Exception
    {
        fixture = Fixtures.newGivenWhenThenFixture(SubmissionAggregate.class);
        Repository<SubmissionAggregate> submissionAggregateRepository = fixture.getRepository();
        SubmissionRepository repo = new AxonSubmissionRepository(submissionAggregateRepository);
        SubmitPhoto submitPhoto = new SubmitPhoto(repo, competitionQuery);
        RetractPhoto retractPhoto = new RetractPhoto(repo);
        fixture.registerAnnotatedCommandHandler(submitPhoto);
        fixture.registerAnnotatedCommandHandler(retractPhoto);

        when(competitionQuery.getCurrent()).thenReturn(
            ImmutablePhotoCompetition.builder().competitionId("current").topic("test").active(true).build());
    }

    @Test
    public void canCreate()
    {

        SubmitPhotoCommand command = ImmutableSubmitPhotoCommand.builder()
            .submissionId("aaa")
            .caption("test")
            .photoContent("xxx".getBytes())
            .uploadedBy("test")
            .competitionId("current")
            .build();

        fixture.given()
            .when(new GenericCommandMessage<>(
                SubmitPhotoCommand.class.getName(), command, null)
            )
            .expectEvents(
                ImmutablePhotoSubmittedEvent.builder()
                    .submissionId("aaa")
                    .caption("test")
                    .photoContent("xxx".getBytes())
                    .uploadedBy("test")
                    .competitionId("current")
                    .build()
            );
    }

    @Test
    public void cantCreateForNonCurrentCompetition()
    {

        SubmitPhotoCommand command = ImmutableSubmitPhotoCommand.builder()
            .submissionId("aaa")
            .caption("test")
            .photoContent("xxx".getBytes())
            .uploadedBy("test")
            .competitionId("not-current")
            .build();

        fixture.given()
            .when(new GenericCommandMessage<>(
                SubmitPhotoCommand.class.getName(), command, null)
            )
            .expectException(RuntimeException.class);
    }

    @Test
    public void canRetract()
    {
        fixture.given(
            ImmutablePhotoSubmittedEvent.builder()
                .submissionId("aaa")
                .caption("test")
                .photoContent("xxx".getBytes())
                .uploadedBy("test")
                .competitionId("current")
                .build()

        )
            .when(
                new GenericCommandMessage<>(
                    RetractPhotoCommand.class.getName(),
                    ImmutableRetractPhotoCommand.builder()
                        .submissionId("aaa").build()
                    , null)
            )
            .expectEvents(
                ImmutableSubmissionRetractedEvent.builder()
                    .submissionId("aaa").build());
    }

    @Test
    public void cantRetractTwice()
    {
        fixture.given(
            ImmutablePhotoSubmittedEvent.builder()
                .submissionId("aaa")
                .caption("test")
                .photoContent("xxx".getBytes())
                .uploadedBy("test")
                .competitionId("current")
                .build(),
            ImmutableSubmissionRetractedEvent.builder()
                .submissionId("aaa").build()
        )
            .when(
                new GenericCommandMessage<>(
                    RetractPhotoCommand.class.getName(),
                    ImmutableRetractPhotoCommand.builder()
                        .submissionId("aaa").build()
                    , null)
            )
            .expectException(RuntimeException.class);
    }
}
