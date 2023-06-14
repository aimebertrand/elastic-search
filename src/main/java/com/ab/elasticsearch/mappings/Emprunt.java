package com.ab.elasticsearch.mappings;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;

@Document(indexName = "emprunt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {
    @Id
    private Integer id;

    @Field(type = FieldType.Date, name = "date_emprunt")
    private Date dateEmprunt;

    @Field(type = FieldType.Date, name = "date_retour")
    private Date dateRetour;

    @Field(type = FieldType.Integer, name = "id_utilisateur")
    @JsonAlias("id_utilisateur")
    private Integer idUtilisateur;

    @Field(type = FieldType.Integer, name = "id_media")
    @JsonAlias("id_media")
    private Integer idMedia;

    @Field(type = FieldType.Text, name = "type_media")
    private String typeMedia;

    public Emprunt(Integer id) {
        this.id = id;
    }
}
