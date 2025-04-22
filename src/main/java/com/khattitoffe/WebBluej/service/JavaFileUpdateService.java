package com.khattitoffe.WebBluej.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.*;

@Service
public class JavaFileUpdateService {
    public ResponseEntity<String> updateFile(String fileName, String code, String username)  throws IOException {
        String fileDir="E:/Spring Boot/data/src/java/"+username+"/";
        //String filename=fileName+".java";
        File file=new File(fileDir+fileName);

        Path filePath = Path.of(fileDir+fileName);
        if (file.exists()){
            try{
                Files.writeString(filePath,code,StandardOpenOption.TRUNCATE_EXISTING);
                return ResponseEntity.ok("File updated successfully");
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Unable to Save File");
            }
        }

        else{
            if(file.createNewFile()){
                try{
                    Files.writeString(filePath,code,StandardOpenOption.TRUNCATE_EXISTING);
                    return ResponseEntity.ok("Updated successfully");
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Unable to Save File");
                }
            }

        }
        return ResponseEntity.badRequest().body("");
    }
}
