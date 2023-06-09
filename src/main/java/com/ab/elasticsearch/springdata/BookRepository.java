package com.ab.elasticsearch.springdata;


import com.ab.elasticsearch.mappings.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/*
 * Allows to create repositories for writing simple CRUD methods.
 * ElasticsearchRestTemplate should be used in order to have more control over the queries
 * */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {
}