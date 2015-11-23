package uk.co.blackpepper.photocomp.active;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableActivePhoto.class)
@JsonDeserialize(as = ImmutableActivePhoto.class)
public interface ActivePhoto
{
    String id();

    String caption();
}
