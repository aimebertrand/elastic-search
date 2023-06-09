package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.springdata.BookRepository;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;

public interface ElasticsearchOperationsService {
    Book create(Book book);

    Book retrieve(String id);

   // UpdateResponse update(Book book);

    String delete(String id);
}
