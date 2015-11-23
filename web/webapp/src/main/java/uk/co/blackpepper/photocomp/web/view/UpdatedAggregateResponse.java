package uk.co.blackpepper.photocomp.web.view;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdatedAggregateResponse.class)
public interface UpdatedAggregateResponse
{
    String id();
}
