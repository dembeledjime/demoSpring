package com.example.demo.modele;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titre;
    String contenu;
    LocalDateTime date_article = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user_id;
    @OneToMany(mappedBy = "article")
    List<Commentaire> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Article [id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", date_article=" + date_article
                + "]";
    }
    
    

}
