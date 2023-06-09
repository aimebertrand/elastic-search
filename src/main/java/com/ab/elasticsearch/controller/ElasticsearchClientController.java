package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.mappings.BookQuery;
import com.ab.elasticsearch.service.ElasticsearchClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/query")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElasticsearchClientController {

    private static final List<String> fields = List.of("title", "content");

    @Autowired
    private ElasticsearchClientService elasticsearchClientService;

    @PostMapping(value = "/must", produces = "application/json")
    public List<Book> fetchBooksWithMustQuery(@RequestBody BookQuery bookQuery) throws IOException {
        return elasticsearchClientService.fetchBooksWithMustQuery(bookQuery);
    }

    @PostMapping(value = "/should", produces = "application/json")
    public List<Book> fetchBooksWithShouldQuery(@RequestBody BookQuery bookQuery) throws IOException {
        return elasticsearchClientService.fetchBooksWithShouldQuery(bookQuery);
    }

    @GetMapping(value = "/{term}", produces = "application/json")
    public List<Book> textSearch(@PathVariable String term) throws IOException {
        return elasticsearchClientService.textSearch(fields, term, 10);
    }
}
