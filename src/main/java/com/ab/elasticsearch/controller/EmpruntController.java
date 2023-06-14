package com.ab.elasticsearch.controller;


import com.ab.elasticsearch.mappings.Emprunt;
import com.ab.elasticsearch.springdata.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/emprunt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpruntController {

    @Autowired
    private EmpruntRepository empruntRepository;

    @PostMapping(value = "/", consumes = "application/json")
    public Emprunt create(@RequestBody Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    @PostMapping(value = "/bulk", consumes = "application/json")
    public Iterable<Emprunt> createBulk(@RequestBody Iterable<Emprunt> empruntList) {
        return empruntRepository.saveAll(empruntList);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Emprunt retrieve(@PathVariable Integer id) {
        return empruntRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ID: " + id));
    }

    @PutMapping(value = "/", consumes = "application/json")
    public Emprunt update(@RequestBody Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        empruntRepository.deleteById(id);
        return "Done";
    }
}
