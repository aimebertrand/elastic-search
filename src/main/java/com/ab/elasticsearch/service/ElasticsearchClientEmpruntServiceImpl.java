package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ab.elasticsearch.mappings.Emprunt;
import com.ab.elasticsearch.mappings.EmpruntQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ElasticsearchClientEmpruntServiceImpl implements ElasticsearchClientEmpruntService {

    private static String index = "emprunt";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<Emprunt> fetchEmpruntWithMustQuery(EmpruntQuery empruntQuery) throws IOException {
        List<Query> queries = prepareQueryList(empruntQuery);
        SearchResponse<Emprunt> empruntSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                empruntQuery.getSize()).query(query -> query.bool(bool -> bool.must(queries))), Emprunt.class);
        return empruntSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Emprunt> fetchEmpruntWithShouldQuery(EmpruntQuery empruntQuery) throws IOException {
        List<Query> queries = prepareQueryList(empruntQuery);
        SearchResponse<Emprunt> empruntSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                empruntQuery.getSize()).query(query -> query.bool(bool -> bool.should(queries))), Emprunt.class);
        return empruntSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Emprunt> textSearch(List<String> fields, String term, int size) throws IOException {
        SearchResponse<Emprunt> empruntSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                size).query(query -> query.multiMatch(multiMatchQuery(fields, term))), Emprunt.class);
        return empruntSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    private List<Query> prepareQueryList(EmpruntQuery empruntQuery) {
        var queryMap = Map.of(
                "id", empruntQuery.getId().toString(),
                "date_emprunt", empruntQuery.getDateEmprunt().toString(),
                "date_retour", empruntQuery.getDateRetour().toString(),
                "id_utilisateur", empruntQuery.getIdUtilisateur().toString(),
                "id_media", empruntQuery.getIdMedia().toString(),
                "type_media", empruntQuery.getTypeMedia()
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
