package com.simon.project;


public class InstaPhoto {
    private String name;
    private String fullUrl;

    InstaPhoto(String name, String fullUrl){
        this.name = name;
        this.fullUrl = fullUrl;
    }

    public String getName(){
        return name;
    }

    public String getFullUrl(){
        return fullUrl;
    }
}
