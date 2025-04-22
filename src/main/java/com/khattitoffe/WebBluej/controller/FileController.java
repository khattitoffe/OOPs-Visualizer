package com.khattitoffe.WebBluej.controller;
import com.khattitoffe.WebBluej.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.khattitoffe.WebBluej.entity.JavaFile;import com.khattitoffe.WebBluej.entity.JavaFileName;import com.khattitoffe.WebBluej.entity.JavaFileUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    FileGet fileGet;

    @Autowired
    JavaFileUpdateService javaFileUpdateService;

    @PostMapping("/uploadJava")
    public ResponseEntity<String> uploadJavaFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
            String username=null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                username = jwtUtil.extractUsername(token);
                System.out.println("username: "+username);
            }
            if(file.isEmpty()) // file non empty honi chahiye
                return ResponseEntity.badRequest().body("Empty file uploaded");
            try {
                if (!file.getOriginalFilename().endsWith(".java"))// ends with shayad null pointer exception dede
                    return ResponseEntity.badRequest().body("The Uploaded file is not a Java File");
            }
            catch (NullPointerException e)
            {
                return ResponseEntity.badRequest().body(e.toString());
            }
            // locally storing the file

        FileUpload upload = new FileUpload(file,username);
        //using fileupload service
        if(upload.uploadJavaFile())
            return ResponseEntity.ok().body("Uploaded File successfully");
        else
            return ResponseEntity.badRequest().body("Failed to upload File");

    }

    @PostMapping("/updateJavaFile")
    public ResponseEntity<String> updateJavaFile(@RequestBody JavaFileUpdate file,HttpServletRequest request) {
        String username = null;

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            System.out.println("username: " + username);
        }

        String filename = file.getClassName();
        String code = file.getCode();
        System.out.println(filename);   
        try {
            return javaFileUpdateService.updateFile(filename, code, username);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to update file");
        }
    }

    @PostMapping("/getJavaFileInfo")
    public JavaFile getInfo(@RequestBody JavaFileName fileName, HttpServletRequest request) {
        String username=null;

        System.out.println("Filename " + fileName.getjavaFileName());
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            System.out.println("username: "+username);
        }

        String javaFile=fileName.getjavaFileName();
        System.out.println(javaFile);
        JavaFileInfo info=new JavaFileInfo(javaFile,username);

        return new JavaFile(info.getClassName(),info.getMethodNames(),info.getSuperClassName(),info.getSuperInterfaceName(),info.getObjectReferences());
    }

    @GetMapping("/getClasses")
    public ResponseEntity<HashMap<String,String>> getClasses(HttpServletRequest request) {
        String username=null;

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            System.out.println("username: "+username);
        }

        HashMap<String,String> classes=new HashMap<>();

        try {
            classes=fileGet.getClasses(username);
        } catch (IOException e) {
            ResponseEntity.badRequest().body(e.toString());
        }

        return ResponseEntity.ok(classes);
    }
}
