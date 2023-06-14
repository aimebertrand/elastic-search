package com.ab.elasticsearch.springdata;


import com.ab.elasticsearch.mappings.Utilisateur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends ElasticsearchRepository<Utilisateur, Integer> {
}
