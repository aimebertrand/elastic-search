package com.ab.elasticsearch.controller;


import com.ab.elasticsearch.mappings.Media;
import com.ab.elasticsearch.springdata.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/media")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;

    @PostMapping(value = "/", consumes = "application/json")
    public Media create(@RequestBody Media media) {
        return mediaRepository.save(media);
    }


    @PostMapping(value = "/bulk", consumes = "application/json")
    public Iterable<Media> createBulk(@RequestBody List<Media> mediaList) {
        return mediaRepository.saveAll(mediaList);
    }

    @GetMapping(value = "/toto" , produces = "application/json")
    public Iterable<Media> getAllMedia() {
        return mediaRepository.findAll();
    }



    @GetMapping(value = "/{id}", produces = "application/json")
    public Media retrieve(@PathVariable Integer id) {
        return mediaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ID: " + id));
    }

    @PutMapping(value = "/", consumes = "application/json")
    public Media update(@RequestBody Media media) {
        return mediaRepository.save(media);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        mediaRepository.deleteById(id);
        return "Done";
    }
}
