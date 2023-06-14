package com.ab.elasticsearch.controller;


import com.ab.elasticsearch.mappings.Utilisateur;
import com.ab.elasticsearch.springdata.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/utilisateur")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping(value = "/", consumes = "application/json")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @PostMapping(value = "/bulk", consumes = "application/json")
    public Iterable<Utilisateur> createBulk(@RequestBody List<Utilisateur> utilisateurList) {
            return utilisateurRepository.saveAll(utilisateurList);
        }

        @GetMapping(value = "/{id}", produces = "application/json")
    public Utilisateur retrieve(@PathVariable Integer id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ID: " + id));
    }

    @PutMapping(value = "/", consumes = "application/json")
    public Utilisateur update(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        utilisateurRepository.deleteById(id);
        return "Done";
    }
}
