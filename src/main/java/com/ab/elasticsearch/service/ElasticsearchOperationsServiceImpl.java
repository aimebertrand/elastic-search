package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchOperationsServiceImpl implements ElasticsearchOperationsService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Override
    public Book create(Book book) {
        return elasticsearchOperations.save(book);
    }

    @Override
    public Book retrieve(String id) {
        return elasticsearchOperations.get(id, Book.class);
    }

  /*  @Override
    public UpdateResponse update(Book book) {
        return elasticsearchOperations.update(book);
    }*/

    @Override
    public String delete(String id) {
        elasticsearchOperations.delete(new Book(id));
        return "Done";
    }
}
