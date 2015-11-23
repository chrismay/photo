package uk.co.blackpepper.photocomp.active;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutablePhotoCompetition.class)
@JsonDeserialize(as = ImmutablePhotoCompetition.class)
public interface PhotoCompetition
{
    String competitionId();

    String topic();

    Set<ActivePhoto> photos();

    Set<String> usersVoted();
}
