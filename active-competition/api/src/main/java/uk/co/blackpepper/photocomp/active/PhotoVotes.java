package uk.co.blackpepper.photocomp.active;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutablePhotoVotes.class)
public interface PhotoVotes
{
    String id();

    String submitter();

    String caption();

    Set<String> voters();

    int totalVotes();
}
