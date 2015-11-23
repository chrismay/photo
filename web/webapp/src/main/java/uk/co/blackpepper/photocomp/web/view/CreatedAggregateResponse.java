package uk.co.blackpepper.photocomp.web.view;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableCreatedAggregateResponse.class)
public interface CreatedAggregateResponse
{
    String id();
}
