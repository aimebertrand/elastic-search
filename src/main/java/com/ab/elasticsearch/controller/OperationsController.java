package com.ab.elasticsearch.controller;


import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.service.ElasticsearchOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/operations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OperationsController {

    @Autowired
    private ElasticsearchOperationsService elasticsearchOperationsService;

    @PostMapping(value = "/", consumes = "application/json")
    public Book create(@RequestBody Book book) {
        return elasticsearchOperationsService.create(book);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book retrieve(@PathVariable String id) {
        return elasticsearchOperationsService.retrieve(id);
    }

  /*  @PutMapping(value = "/", consumes = "application/json")
    public UpdateResponse update(@RequestBody Book book) {
        return elasticsearchOperationsService.update(book);
    }*/

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable String id) {
        elasticsearchOperationsService.delete(id);
        return "Done";
    }
}
