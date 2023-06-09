package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.mappings.BookQuery;

import java.io.IOException;
import java.util.List;

public interface ElasticsearchClientService {

    List<Book> fetchBooksWithMustQuery(BookQuery bookQuery) throws IOException;

    List<Book> fetchBooksWithShouldQuery(BookQuery bookQuery) throws IOException;

    List<Book> textSearch(List<String> fields, String term, int size) throws IOException;
}
