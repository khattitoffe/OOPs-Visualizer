package com.khattitoffe.WebBluej.service;
import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


public class JavaFileInfo implements FileInfo{
    private String fileName;
    private String fileLoaction="E:/Spring Boot/data/src/java/";
    private File file;
    private CompilationUnit cu;
    private String className=""; // classname
    private String parentClassName=""; // parent class name
    private HashMap<String,String> methodsName=new HashMap<>(); // all methods
    private ArrayList<String> interfaceName=new ArrayList<>(); // all interface it implements
    private HashMap<String,String> objectRefernces=new HashMap<>();



    public JavaFileInfo(String fileName){
        this.fileName=fileName+".java";
        file=new File(fileLoaction+this.fileName);

        initialize();
        extractInfo();
    }


    public void initialize(){
        JavaParser parser = new JavaParser();

        try {
            ParseResult<CompilationUnit> result = parser.parse(file);
            if(result.getResult().isPresent())
            {
                cu = result.getResult().get();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void extractInfo()
    {

        for (ClassOrInterfaceDeclaration clazz : cu.findAll(ClassOrInterfaceDeclaration.class)) {

            className=clazz.getNameAsString();

            clazz.getExtendedTypes().forEach(parent ->
                    parentClassName=parent.getNameAsString()
            );
            clazz.getImplementedTypes().forEach(interf ->
                    interfaceName.add(interf.getNameAsString())
            );

            clazz.getMethods().forEach(method ->
                    methodsName.put(method.getDeclarationAsString(true,false,true),method.getNameAsString())
            );
        }

        for (VariableDeclarationExpr varExpr : cu.findAll(VariableDeclarationExpr.class)) {
            for (VariableDeclarator var : varExpr.getVariables()) {
                String type = var.getType().asString();

                if (!var.getType().isPrimitiveType()) {
                   objectRefernces.put(var.getNameAsString(),type);
                   // System.out.println("Object reference variable found: " + var.getNameAsString() + " of type " + type);
                }
            }
        }

    }

    @Override
    public String getClassName() {
        return className;
    }
    @Override
    public HashMap<String,String> getMethodNames() {
        return methodsName;
       // return methodsName.toArray(String[]::new);
    }
    @Override
    public String getSuperClassName() {
        return parentClassName;
    }
    @Override
    public String[] getSuperInterfaceName(){
        return interfaceName.toArray(String[]::new);
    }
    @Override
    public HashMap<String,String> getObjectReferences() {
        return objectRefernces;
    }

    @Override
    public boolean isInterface(){

        return true; // kya interface hai ya nahie true/false returm type
    }
}
