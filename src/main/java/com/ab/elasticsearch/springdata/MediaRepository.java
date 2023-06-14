package com.ab.elasticsearch.springdata;


import com.ab.elasticsearch.mappings.Media;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends ElasticsearchRepository<Media, Integer> {
}

