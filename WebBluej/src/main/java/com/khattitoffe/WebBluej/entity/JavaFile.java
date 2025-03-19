package com.khattitoffe.WebBluej.entity;

public class JavaFile {
    private String filename;
    private String[] methods;
    private String superClasses;

    public JavaFile(String filename, String[] methods, String superClasses) {
        this.filename = filename;
        this.methods = methods;
        this.superClasses = superClasses;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String[] getMethods() {
        return methods;
    }
    public void setMethods(String[] methods) {
        this.methods = methods;
    }
    public String getSuperClasses() {
        return superClasses;
    }
    public void setSuperClasses(String superClasses) {
        this.superClasses = superClasses;
    }
}
