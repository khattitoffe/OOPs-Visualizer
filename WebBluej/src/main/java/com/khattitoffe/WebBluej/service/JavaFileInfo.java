package com.khattitoffe.WebBluej.service;
import java.lang.reflect.*;
public class JavaFileInfo implements FileInfo{
     private String fileName="";

    public JavaFileInfo(String fileName){
        this.fileName=fileName;
    }
    Class<?> myClass=fileName.getClass();

    @Override
    public String getClassName() {
        return myClass.getName();
    }
    @Override
    public String[] getMethodNames() {
        int i=0;
        String[] allMethods=new String[myClass.getDeclaredMethods().length];
        Method[] methods=myClass.getDeclaredMethods();
        for(Method m: methods)
        {
            allMethods[i]=m.getName();
            i++;
        }
        // inbuilt methods bhi arre hai user defined akele ni aaree
        return allMethods;
    }
    @Override
    public String getSuperClassName() {
        return myClass.getSuperclass().getName();// supers class ka naam ayega
    }
    @Override
    public boolean isInterface(){
        return myClass.isInterface(); // kya interface hai ya nahie true/false returm type
    }
}
