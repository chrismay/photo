package uk.co.blackpepper.photocomp.submittedphoto;

import java.util.Set;

import org.immutables.value.Value;

@Value.Immutable
public interface SubmittedPhotoMetadata
{

    String getCaption();

    Set<String> getVoters();
}
