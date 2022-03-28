package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.modele.User;

@Service
@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

    User findUserByLoginAndMdp( String login, String mdp );

}
