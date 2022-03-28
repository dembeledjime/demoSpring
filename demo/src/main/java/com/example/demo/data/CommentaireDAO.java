package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.modele.Commentaire;

@Service
public interface CommentaireDAO extends JpaRepository<Commentaire, Long>{

}
