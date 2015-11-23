package uk.co.blackpepper.photocomp.submittedphoto;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableSubmittedPhoto.class)
@JsonDeserialize(as = ImmutableSubmittedPhoto.class)
public interface SubmittedPhoto
{
    byte[] getContent();
}
