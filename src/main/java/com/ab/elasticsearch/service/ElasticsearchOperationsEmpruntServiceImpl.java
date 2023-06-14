package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchOperationsEmpruntServiceImpl implements ElasticsearchOperationsEmpruntService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Emprunt create(Emprunt emprunt) {
        return elasticsearchOperations.save(emprunt);
    }

    @Override
    public Emprunt retrieve(Integer id) {
        return elasticsearchOperations.get(id.toString(), Emprunt.class);
    }


    /*@Override
    public UpdateResponse update(Emprunt emprunt) {
        return elasticsearchOperations.update(emprunt);
    }*/

    @Override
    public String delete(Integer id) {
        elasticsearchOperations.delete(new Emprunt(id));
        return "Done";
    }
}

