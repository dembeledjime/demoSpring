package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.ArticleDAO;
import com.example.demo.modele.Article;
import com.example.demo.modele.Commentaire;
import com.example.demo.modele.User;

@Controller
public class ArticleCtl {
    
    @Autowired
    ArticleDAO articleDAO;
        
    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute( "articles", articleDAO.findAll() );
        
        return "index";
    }
    
    @GetMapping("/article")
    public String article(@RequestParam Long idArt, Model model) {
        Commentaire comment = new Commentaire();
        comment.setArticle( articleDAO.findById( idArt ).get() );
        
        model.addAttribute( "article", articleDAO.findById( idArt ).get() );
        model.addAttribute( "comment", comment );
       
        return "article";
    }
    
    @GetMapping("/article/new") //@ResponseBody
    public String newArticle(Model model, @ModelAttribute Article article, HttpSession session) {
        User user =  (User) session.getAttribute( "user" );
        
        article.setUser_id( user );
        model.addAttribute( "newArticle", article );

        return "articleNew";
    }
    
    @PostMapping("/new/article")
    public String newArt(@ModelAttribute Article article){
        articleDAO.save( article );
        
        return "redirect:/.";
    }

}
