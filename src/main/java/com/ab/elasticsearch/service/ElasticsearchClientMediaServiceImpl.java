package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ab.elasticsearch.mappings.Media;
import com.ab.elasticsearch.mappings.MediaQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ElasticsearchClientMediaServiceImpl implements ElasticsearchClientMediaService {

    private static String index = "media";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<Media> fetchMediaWithMustQuery(MediaQuery mediaQuery) throws IOException {
        List<Query> queries = prepareQueryList(mediaQuery);
        SearchResponse<Media> mediaSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                mediaQuery.getSize()).query(query -> query.bool(bool -> bool.must(queries))), Media.class);
        return mediaSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Media> fetchMediaWithShouldQuery(MediaQuery mediaQuery) throws IOException {
        List<Query> queries = prepareQueryList(mediaQuery);
        SearchResponse<Media> mediaSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                mediaQuery.getSize()).query(query -> query.bool(bool -> bool.should(queries))), Media.class);
        return mediaSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }


    @Override
    public List<Media> textSearch(List<String> fields, String term, int size) throws IOException {
        SearchResponse<Media> mediaSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                size).query(query -> query.multiMatch(multiMatchQuery(fields, term))), Media.class);
        return mediaSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }



    private List<Query> prepareQueryList(MediaQuery mediaQuery) {
        var queryMap = Map.of(
                "id", mediaQuery.getId().toString(),
                "type", mediaQuery.getType(),
                "titre", mediaQuery.getTitre(),
                "auteur_realisateur_editeur", mediaQuery.getAuteurRealisateurEditeur(),
                "disponibilite", mediaQuery.getDisponibilite().toString()
        );

        var queries = queryMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(entry -> termQuery(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
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
