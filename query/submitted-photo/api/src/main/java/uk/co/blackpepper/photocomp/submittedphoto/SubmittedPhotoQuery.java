package uk.co.blackpepper.photocomp.submittedphoto;

public interface SubmittedPhotoQuery
{
    SubmittedPhoto getByID(String id);

    SubmittedPhotoMetadata getMetadataById(String id);
}
