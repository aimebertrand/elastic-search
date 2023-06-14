package com.ab.elasticsearch.mappings;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MediaQuery {
    private Integer id;
    private String type;
    private String titre;
    private String auteurRealisateurEditeur;
    private Boolean disponibilite;
    private int size;
}
