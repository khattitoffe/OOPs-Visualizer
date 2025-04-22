package com.khattitoffe.WebBluej.service;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FileGet{
    private ArrayList<File> files=new ArrayList<>();
    private HashMap<String,String> classes=new HashMap<>();

    public HashMap<String, String> getClasses(String username) throws IOException
    {
        String fileDir="E:/Spring Boot/data/src/java/"+username+"/";
        File dir=new File(fileDir);

        String[] files=dir.list();
        File Class=null;
        String classContent="";
        for(String file:files)
        {
            Class=new File(fileDir+file);
            classContent= Files.readString(Class.toPath());
            classes.put(file,classContent);
        }

        return classes;
    }
}
