package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Emprunt;
import com.ab.elasticsearch.mappings.EmpruntQuery;
import com.ab.elasticsearch.service.ElasticsearchClientEmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/query/emprunt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElasticsearchClientEmpruntController {

    private static final List<String> fields = List.of("date_emprunt", "date_retour");

    @Autowired
    private ElasticsearchClientEmpruntService elasticsearchClientService;

    @PostMapping(value = "/must", produces = "application/json")
    public List<Emprunt> fetchEmpruntWithMustQuery(@RequestBody EmpruntQuery empruntQuery) throws IOException {
        return elasticsearchClientService.fetchEmpruntWithMustQuery(empruntQuery);
    }

    @PostMapping(value = "/should", produces = "application/json")
    public List<Emprunt> fetchEmpruntWithShouldQuery(@RequestBody EmpruntQuery empruntQuery) throws IOException {
        return elasticsearchClientService.fetchEmpruntWithShouldQuery(empruntQuery);
    }

    @GetMapping(value = "/{term}", produces = "application/json")
    public List<Emprunt> textSearch(@PathVariable String term) throws IOException {
        return elasticsearchClientService.textSearch(fields, term, 10);
    }
}
