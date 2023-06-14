package com.ab.elasticsearch.mappings;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "media")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, name = "type")
    private String type;

    @Field(type = FieldType.Text, name = "titre")
    private String titre;

    @Field(type = FieldType.Text, name = "auteur_realisateur_editeur")
    @JsonAlias("auteur_realisateur_editeur")
    private String auteurRealisateurEditeur;

    @Field(type = FieldType.Boolean, name = "disponibilite")
    private Boolean disponibilite;

    public Media(Integer id) {
        this.id = id;
    }
}