package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ab.elasticsearch.mappings.MediaQuery;
import com.ab.elasticsearch.mappings.Media;
import com.ab.elasticsearch.mappings.MediaQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ElasticsearchClientMediaService {
    List<Media> fetchMediaWithMustQuery(MediaQuery mediaQuery) throws IOException;
    List<Media> fetchMediaWithShouldQuery(MediaQuery mediaQuery) throws IOException;
    List<Media> textSearch(List<String> fields, String term, int size) throws IOException;
}

