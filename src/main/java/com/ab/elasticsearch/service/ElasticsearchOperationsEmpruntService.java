package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Emprunt;

public interface ElasticsearchOperationsEmpruntService {
    Emprunt create(Emprunt emprunt);
    Emprunt retrieve(Integer id);
    // UpdateResponse update(Emprunt emprunt);
    String delete(Integer id);
}
