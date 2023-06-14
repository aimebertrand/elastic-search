package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Utilisateur;
import com.ab.elasticsearch.service.ElasticsearchOperationsUtilisateurService;
import com.ab.elasticsearch.service.ElasticsearchOperationsUtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/operations/utilisateur")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtilisateurOperationsController {

    @Autowired
    private ElasticsearchOperationsUtilisateurService utilisateurOperationsService;

    @PostMapping(value = "/", consumes = "application/json")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurOperationsService.create(utilisateur);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Utilisateur retrieve(@PathVariable Integer id) {
        return utilisateurOperationsService.retrieve(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        utilisateurOperationsService.delete(id);
        return "Done";
    }
}
