package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.modele.Article;

@Service
public interface ArticleDAO extends JpaRepository<Article, Long> {

}
