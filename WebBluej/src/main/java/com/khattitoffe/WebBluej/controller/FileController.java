package com.khattitoffe.WebBluej.controller;
import org.springframework.web.bind.annotation.*;
import com.khattitoffe.WebBluej.service.FileUpload;import com.khattitoffe.WebBluej.service.JavaFileInfo;
import com.khattitoffe.WebBluej.entity.JavaFile;import com.khattitoffe.WebBluej.entity.JavaFileName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/uploadJava")
    public ResponseEntity<String> uploadJavaFile(@RequestParam("file") MultipartFile file) {

            if(file.isEmpty()) // file non empty honi chahiye
                return ResponseEntity.badRequest().body("Empty file uploaded");
            try {
                if (!file.getOriginalFilename().endsWith(".java"))// ends with shayad null pointer exception dede
                    return ResponseEntity.badRequest().body("The Uploaded file is not a .java File");
            }
            catch (NullPointerException e)
            {
                return ResponseEntity.badRequest().body(e.toString());
            }
            // locally storing the file

        FileUpload upload = new FileUpload(file);
            //using fileupload service
        if(upload.uploadJavaFile())
            return ResponseEntity.ok().body("Uploaded File successfully");
        else
            return ResponseEntity.badRequest().body("Failed to upload File");

    }

    @GetMapping("/getJavaFileInfo")
    public JavaFile getInfo(@RequestBody JavaFileName fileName) {
        String javafile=fileName.getJavaFileName();

        JavaFileInfo info=new JavaFileInfo(javafile);

        return new JavaFile(info.getClassName(),info.getMethodNames(),info.getSuperClassName());
    }
}
