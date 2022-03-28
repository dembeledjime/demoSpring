package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.CommentaireDAO;
import com.example.demo.modele.Commentaire;

@Controller
public class CommentCtl {
    
    @Autowired
    CommentaireDAO commentaireDAO;
    
    @PostMapping("/article/comment")
    public String newComment(@ModelAttribute Commentaire commentaire) {
        System.out.println( commentaire );
        commentaireDAO.save( commentaire );
        
        return "redirect:/article?idArt="+commentaire.getArticle().getId();
    }

}
