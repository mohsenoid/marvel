package com.mirhoseini.marvel.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Items {

    @SerializedName("resourceURI")
    private String resourceURI;
    @SerializedName("name")
    private String name;

    public Items() {
    }

    public Items(String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
