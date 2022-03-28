package com.example.demo.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String sexe;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 20, message = "Longeur entre 2 et 20")
	private String prenom;
	private String nom;
	
	@Column(unique = true)
	private String login;
	private String mdp; 
	private int age;
	private String statut = "user";
	@Column(nullable = true, length = 70)
	private String img;
	
	@OneToMany(mappedBy = "user_id")
	private List<Article> articles = new ArrayList<>();

    @Override
    public String toString() {
        return "User [id=" + id + ", sexe=" + sexe + ", prenom=" + prenom + ", nom=" + nom + ", login=" + login
                + ", mdp=" + mdp + ", age=" + age + ", statut=" + statut + ", img="+ img + "]";
    }
	
    public String getPhotoPath() {
        return img != null ? "/img/user/"+img : null;
    }
	
	
}
