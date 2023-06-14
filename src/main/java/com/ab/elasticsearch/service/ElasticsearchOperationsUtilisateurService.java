package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Utilisateur;

public interface ElasticsearchOperationsUtilisateurService {
    Utilisateur create(Utilisateur utilisateur);
    Utilisateur retrieve(Integer id);
    // UpdateResponse update(Utilisateur utilisateur);
    String delete(Integer id);
}
