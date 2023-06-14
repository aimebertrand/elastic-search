package com.ab.elasticsearch.service;

import com.ab.elasticsearch.mappings.Media;

public interface ElasticsearchOperationsMediaService {
    Media create(Media media);
    Media retrieve(Integer id);
    // UpdateResponse update(Media media);
    String delete(Integer id);
}