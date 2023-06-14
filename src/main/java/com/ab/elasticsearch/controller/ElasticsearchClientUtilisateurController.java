package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Utilisateur;
import com.ab.elasticsearch.mappings.UtilisateurQuery;
import com.ab.elasticsearch.service.ElasticsearchClientUtilisateurService;
import com.ab.elasticsearch.service.ElasticsearchClientUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/query/utilisateur")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElasticsearchClientUtilisateurController {

    private static final List<String> fields = List.of("nom", "adresse");

    @Autowired
    private ElasticsearchClientUtilisateurService elasticsearchClientService;

    @PostMapping(value = "/must", produces = "application/json")
    public List<Utilisateur> fetchUtilisateurWithMustQuery(@RequestBody UtilisateurQuery utilisateurQuery) throws IOException {
        return elasticsearchClientService.fetchUtilisateurWithMustQuery(utilisateurQuery);
    }

    @PostMapping(value = "/should", produces = "application/json")
    public List<Utilisateur> fetchUtilisateurWithShouldQuery(@RequestBody UtilisateurQuery utilisateurQuery) throws IOException {
        return elasticsearchClientService.fetchUtilisateurWithShouldQuery(utilisateurQuery);
    }

    @GetMapping(value = "/{term}", produces = "application/json")
    public List<Utilisateur> textSearch(@PathVariable String term) throws IOException {
        return elasticsearchClientService.textSearch(fields, term, 10);
    }
}

