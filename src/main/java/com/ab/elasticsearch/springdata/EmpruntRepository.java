package com.ab.elasticsearch.springdata;


import com.ab.elasticsearch.mappings.Emprunt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends ElasticsearchRepository<Emprunt, Integer> {
}

