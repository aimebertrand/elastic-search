package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ab.elasticsearch.mappings.Utilisateur;
import com.ab.elasticsearch.mappings.UtilisateurQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ElasticsearchClientUtilisateurServiceImpl implements ElasticsearchClientUtilisateurService {

    private static String index = "utilisateur";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<Utilisateur> fetchUtilisateurWithMustQuery(UtilisateurQuery utilisateurQuery) throws IOException {
        List<Query> queries = prepareQueryList(utilisateurQuery);
        SearchResponse<Utilisateur> utilisateurSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                utilisateurQuery.getSize()).query(query -> query.bool(bool -> bool.must(queries))), Utilisateur.class);
        return utilisateurSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Utilisateur> fetchUtilisateurWithShouldQuery(UtilisateurQuery utilisateurQuery) throws IOException {
        List<Query> queries = prepareQueryList(utilisateurQuery);
        SearchResponse<Utilisateur> utilisateurSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                utilisateurQuery.getSize()).query(query -> query.bool(bool -> bool.should(queries))), Utilisateur.class);
        return utilisateurSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    @Override
    public List<Utilisateur> textSearch(List<String> fields, String term, int size) throws IOException {
        SearchResponse<Utilisateur> utilisateurSearchResponse = elasticsearchClient.search(req -> req.index(index).size(
                size).query(query -> query.multiMatch(multiMatchQuery(fields, term))), Utilisateur.class);
        return utilisateurSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    private List<Query> prepareQueryList(UtilisateurQuery utilisateurQuery) {
        var queryMap = Map.of(
                "id", utilisateurQuery.getId().toString(),
                "nom", utilisateurQuery.getNom(),
                "adresse", utilisateurQuery.getAdresse()
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
