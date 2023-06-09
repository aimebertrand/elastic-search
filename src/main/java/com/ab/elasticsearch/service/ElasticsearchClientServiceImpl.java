package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ab.elasticsearch.mappings.Book;
import com.ab.elasticsearch.mappings.BookQuery;
import com.ab.elasticsearch.springdata.BookRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @Slf4j public class ElasticsearchClientServiceImpl implements ElasticsearchClientService {

    private static String index = "blog";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<Book> fetchBooksWithMustQuery(BookQuery bookQuery) throws IOException {
        List<Query> queries = prepareQueryList(bookQuery);
        SearchResponse<Book> bookSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                bookQuery.getSize()).query(query -> query.bool(bool -> bool.must(queries))), Book.class);
        return bookSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Book> fetchBooksWithShouldQuery(BookQuery bookQuery) throws IOException {
        List<Query> queries = prepareQueryList(bookQuery);
        SearchResponse<Book> bookSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                bookQuery.getSize()).query(query -> query.bool(bool -> bool.should(queries))), Book.class);
        return bookSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Book> textSearch(List<String> fields, String term, int size) throws IOException {
        SearchResponse<Book> bookSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                size).query(query -> query.multiMatch(multiMatchQuery(fields, term))), Book.class);
        return bookSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    private List<Query> prepareQueryList(BookQuery bookQuery) {
        var queryMap = Map.of("category_id", bookQuery.getCategoryId(), "author_id", bookQuery.getAuthorId());

        var queries = queryMap.entrySet().stream().filter(entry -> !ObjectUtils.isEmpty(entry.getValue())).map(
                entry -> termQuery(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        log.debug("queries: " + queries);
        return queries;
    }

    public Query termQuery(String field, String value) {
        QueryVariant queryVariant = new TermQuery.Builder().caseInsensitive(true).field(field).value(value).build();
        return new Query(queryVariant);
    }

    public MultiMatchQuery multiMatchQuery(List<String> fields, String value) {
        return new MultiMatchQuery.Builder().fields(fields).query(value).build();
    }
}
