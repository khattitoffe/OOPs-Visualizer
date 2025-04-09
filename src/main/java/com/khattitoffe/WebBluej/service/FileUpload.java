package com.khattitoffe.WebBluej.service;
import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    MultipartFile file=null;

    public FileUpload(MultipartFile file) {
        this.file = file;
    }

    public boolean uploadJavaFile() {
        String fileDir="E:/Spring Boot/data/src/java/";
        String filename=file.getOriginalFilename();

        try{
            File dir=new File(fileDir);
            if(!dir.exists()) {
                if(!dir.mkdirs()) //dir made // mkdir returns boolean.. agar false aya toh ye true hoke
                {
                    return false;
                }
            }
            File javafile=new File(fileDir+filename);
            file.transferTo(javafile);  // file stored to dir

            return true;//file uploaded
        }
        catch (IOException e)
        {
            return false;//file not uploaded
        }
    }
}
