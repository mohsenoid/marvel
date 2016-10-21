package com.mirhoseini.marvel.domain.model;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Thumbnail {

    private String path;

    private String extension;

    public Thumbnail() {
    }

    public Thumbnail(String path, String extension) {
        this.extension = extension;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
