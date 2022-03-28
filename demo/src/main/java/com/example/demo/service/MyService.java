package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class MyService {
    
    public static void uploadFile(String uploadDir, String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get( uploadDir );
        
        try(InputStream inputStream = multipartFile.getInputStream()) {   
            Path filePath = uploadPath.resolve( fileName );
            Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );
        } catch ( IOException e ) {
            System.out.println( "impossible de copier cette image !! " + fileName );
        }
    }

}
