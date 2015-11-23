package uk.co.blackpepper.photocomp.active;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableCompetitionResults.class)
public interface CompetitionResults
{
    String topic();

    String competitionId();

    Set<PhotoVotes> photos();
}
