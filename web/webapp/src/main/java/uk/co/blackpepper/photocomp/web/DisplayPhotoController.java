package uk.co.blackpepper.photocomp.web;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayPhotoController
{
    @RequestMapping()
    public ResponseEntity<InputStreamResource> show(@PathVariable String id)
    {
        InputStreamResource s = new InputStreamResource(new ByteArrayInputStream("foo".getBytes()));
        return ResponseEntity.ok(s);
    }
}
