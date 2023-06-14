package com.ab.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ab.elasticsearch.mappings.Utilisateur;
import com.ab.elasticsearch.mappings.UtilisateurQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch.core.search.Hit;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ElasticsearchClientUtilisateurService {
    List<Utilisateur> fetchUtilisateurWithMustQuery(UtilisateurQuery utilisateurQuery) throws IOException;
    List<Utilisateur> fetchUtilisateurWithShouldQuery(UtilisateurQuery utilisateurQuery) throws IOException;
    List<Utilisateur> textSearch(List<String> fields, String term, int size) throws IOException;
}

