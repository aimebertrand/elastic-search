package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchOperationsMediaServiceImpl implements ElasticsearchOperationsMediaService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Media create(Media media) {
        return elasticsearchOperations.save(media);
    }

    @Override
    public Media retrieve(Integer id) {
        return elasticsearchOperations.get(id.toString(), Media.class);
    }


    /*@Override
    public UpdateResponse update(Media media) {
        return elasticsearchOperations.update(media);
    }*/

    @Override
    public String delete(Integer id) {
        elasticsearchOperations.delete(new Media(id));
        return "Done";
    }
}

