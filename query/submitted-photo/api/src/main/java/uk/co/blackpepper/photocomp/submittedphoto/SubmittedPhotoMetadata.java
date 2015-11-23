package uk.co.blackpepper.photocomp.submittedphoto;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableSubmittedPhotoMetadata.class)
@JsonDeserialize(as = ImmutableSubmittedPhotoMetadata.class)
public interface SubmittedPhotoMetadata
{

    String getCaption();

    Set<String> getVoters();
}
