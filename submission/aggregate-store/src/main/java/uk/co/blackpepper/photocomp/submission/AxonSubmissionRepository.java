package uk.co.blackpepper.photocomp.submission;

import org.axonframework.repository.Repository;

import uk.co.blackpepper.photocomp.submission.api.commands.SubmitPhotoCommand;

public class AxonSubmissionRepository implements SubmissionRepository
{
    private final Repository<SubmissionAggregate> repository;

    public AxonSubmissionRepository(Repository<SubmissionAggregate> repository)
    {
        this.repository = repository;
    }

    @Override
    public Submission load(String id)
    {
        return repository.load(id);
    }

    @Override
    public void add(SubmitPhotoCommand command)
    {
        repository.add(new SubmissionAggregate(command));
    }
}
