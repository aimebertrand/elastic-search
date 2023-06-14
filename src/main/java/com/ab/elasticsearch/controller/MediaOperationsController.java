package com.ab.elasticsearch.controller;


import com.ab.elasticsearch.mappings.Media;
import com.ab.elasticsearch.service.ElasticsearchOperationsMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/operations/media")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MediaOperationsController {

    @Autowired
    private ElasticsearchOperationsMediaService mediaOperationsService;

    @PostMapping(value = "/", consumes = "application/json")
    public Media create(@RequestBody Media media) {
        return mediaOperationsService.create(media);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Media retrieve(@PathVariable Integer id) {
        return mediaOperationsService.retrieve(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        mediaOperationsService.delete(id);
        return "Done";
    }
}
