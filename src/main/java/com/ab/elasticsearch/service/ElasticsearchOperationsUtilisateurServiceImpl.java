package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchOperationsUtilisateurServiceImpl implements ElasticsearchOperationsUtilisateurService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Utilisateur create(Utilisateur utilisateur) {
        return elasticsearchOperations.save(utilisateur);
    }

    @Override
    public Utilisateur retrieve(Integer id) {
        return elasticsearchOperations.get(id.toString(), Utilisateur.class);

    }

    @Override
    public String delete(Integer id) {
       elasticsearchOperations.delete(new Utilisateur(id));
        return "Done";
    }


    /*@Override
    public UpdateResponse update(Utilisateur utilisateur) {
        return elasticsearchOperations.update(utilisateur);
    }*/


}
