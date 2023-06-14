package com.ab.elasticsearch.mappings;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class EmpruntQuery {
    private Integer id;
    private Date dateEmprunt;
    private Date dateRetour;
    private Integer idUtilisateur;
    private Integer idMedia;
    private String typeMedia;
    private int size;
}
