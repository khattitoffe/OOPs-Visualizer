package com.khattitoffe.WebBluej.service;

public interface FileInfo {
    public abstract String getClassName();
    public abstract String[] getMethodNames();
    public abstract String getSuperClassName();
    public abstract boolean isInterface();

}
