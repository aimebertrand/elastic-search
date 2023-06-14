package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Emprunt;
import com.ab.elasticsearch.service.ElasticsearchOperationsEmpruntService;
import com.ab.elasticsearch.service.ElasticsearchOperationsEmpruntServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/operations/emprunt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpruntOperationsController {

    @Autowired
    private ElasticsearchOperationsEmpruntService empruntOperationsService;

    @PostMapping(value = "/", consumes = "application/json")
    public Emprunt create(@RequestBody Emprunt emprunt) {
        return empruntOperationsService.create(emprunt);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Emprunt retrieve(@PathVariable Integer id) {
        return empruntOperationsService.retrieve(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id) {
        empruntOperationsService.delete(id);
        return "Done";
    }
}
