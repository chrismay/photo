package uk.co.blackpepper.photocomp.active;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableActiveCompetition.class)
@JsonDeserialize(as = ImmutableActiveCompetition.class)
public interface ActiveCompetition
{
    String competitionId();

    String topic();

    Set<String> photoIds();
}
