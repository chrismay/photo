package uk.co.blackpepper.photocomp.submittedphoto;

import org.immutables.value.Value;

@Value.Immutable
public interface SubmittedPhoto
{
    byte[] getContent();
}
