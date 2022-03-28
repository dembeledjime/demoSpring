package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.UserDAO;
import com.example.demo.modele.User;
import com.example.demo.service.MyService;

@Service
@Controller
public class PersonneCtl {
	
	@Autowired
	UserDAO userDAO; 
	
	
	@GetMapping("/users/liste")
	public String home(Model model) {
		model.addAttribute("personnes", userDAO.findAll() );

		return "users";
	}
	
	@GetMapping("/user")
	public String user(Model model, @RequestParam Integer id) {
	    model.addAttribute( "user", userDAO.findById( id ).get() );
	    
	    return "detail";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("newMembre", new User());

		return "form";
	}
		
	@PostMapping("/form")
	public String addPersonne(@Valid @ModelAttribute("newMembre") User personne, 
	                          BindingResult result,
	                          Model model,
	                          @RequestParam("logo") MultipartFile multipartFile) {
	    
	    if( result.hasErrors() ) {
	        model.addAttribute("newMembre", personne);
	        model.addAttribute( "errors", result );

	        return "form";
	    }
        String fileName = StringUtils.cleanPath( multipartFile.getOriginalFilename() );
        
        if( !fileName.isEmpty() ) {
            String uploadDir = "src\\main\\resources\\static\\img\\user\\";
            
            MyService.uploadFile( uploadDir, fileName, multipartFile );
            
            personne.setImg( fileName );
        }
        
        userDAO.save(personne);

		return "redirect:.";
	}
	
	@GetMapping("/form/connect")
    public String connextion() {
        return "connexion";
    }
	
	@PostMapping("/form/connect")
    public String login(@RequestParam String login, @RequestParam String mdp, HttpSession session) {
	    
	    User user = userDAO.findUserByLoginAndMdp(login, mdp);
        session.setAttribute( "user", user );
        
        return "redirect:/.";
    }
	
	@GetMapping("/user/logout")
    public String logout(HttpSession session) {
	    session.invalidate();

	    return "redirect:/.";
    }
	
	@GetMapping("/update")
    public String update(Model model, @RequestParam Integer id) {
        User user = userDAO.getById(id);
      //  model.addAttribute( "user", user );
        model.addAttribute("newMembre", user);

        return "form";
    }
	
	@GetMapping("/delete")
	public String supprimer(Model model, @RequestParam Integer id) {
		User user = userDAO.getById(id);
		
		try {
            Files.delete( Paths.get( "src\\main\\resources\\static\\img\\user\\" + user.getImg()) );
        } catch ( IOException e ) {
            System.out.println( "PAS DE FILE" );
        }	
		
		userDAO.delete(user);
 
		return "redirect:.";
	}
	
	 
}
