package uk.co.blackpepper.photocomp.web;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.blackpepper.photocomp.submittedphoto.SubmittedPhoto;
import uk.co.blackpepper.photocomp.submittedphoto.SubmittedPhotoQuery;

@Controller
public class DisplayPhotoController
{

    private final SubmittedPhotoQuery photos;

    @Autowired
    public DisplayPhotoController(SubmittedPhotoQuery photos)
    {
        this.photos = photos;
    }

    @RequestMapping(value = "/photo/{id}", produces = "image/jpeg")
    public ResponseEntity<InputStreamResource> show(@PathVariable String id)
    {
        SubmittedPhoto submittedPhoto = photos.getByID(id);
        if (submittedPhoto == null)
        {
            return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
        }
        InputStreamResource s = new InputStreamResource(new ByteArrayInputStream(submittedPhoto.getContent()));
        return ResponseEntity.ok(s);
    }
}
