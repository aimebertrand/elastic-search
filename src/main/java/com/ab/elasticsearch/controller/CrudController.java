package com.ab.elasticsearch.controller;

import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.springdata.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CrudController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value = "/", consumes = "application/json")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book retrieve(@PathVariable String id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ID: " + id));
    }

    @PutMapping(value = "/", consumes = "application/json")
    public Book update(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable String id) {
        bookRepository.deleteById(id);
        return "Done";
    }
}
