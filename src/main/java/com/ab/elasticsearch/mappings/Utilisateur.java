package com.ab.elasticsearch.mappings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, name = "nom")
    private String nom;

    @Field(type = FieldType.Text, name = "adresse")
    private String adresse;


    public Utilisateur(Integer id) {
        this.id = id;
    }
}
