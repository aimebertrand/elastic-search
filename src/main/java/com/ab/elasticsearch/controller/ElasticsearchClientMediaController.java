package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Media;
import com.ab.elasticsearch.mappings.MediaQuery;
import com.ab.elasticsearch.service.ElasticsearchClientMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/query/media")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElasticsearchClientMediaController {

    private static final List<String> fields = List.of("titre", "auteur_realisateur_editeur");

    @Autowired
    private ElasticsearchClientMediaService elasticsearchClientService;

    @PostMapping(value = "/must", produces = "application/json")
    public List<Media> fetchMediaWithMustQuery(@RequestBody MediaQuery mediaQuery) throws IOException {
        return elasticsearchClientService.fetchMediaWithMustQuery(mediaQuery);
    }

    @PostMapping(value = "/should", produces = "application/json")
    public List<Media> fetchMediaWithShouldQuery(@RequestBody MediaQuery mediaQuery) throws IOException {
        return elasticsearchClientService.fetchMediaWithShouldQuery(mediaQuery);
    }

    @GetMapping(value = "/{term}", produces = "application/json")
    public List<Media> textSearch(@PathVariable String term) throws IOException {
        return elasticsearchClientService.textSearch(fields, term, 10);
    }
}
