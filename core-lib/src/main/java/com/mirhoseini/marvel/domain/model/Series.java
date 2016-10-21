package com.mirhoseini.marvel.domain.model;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Series {

    private Items[] items;

    private String collectionURI;

    private String available;

    private String returned;

    public Series() {
    }

    public Series(Items[] items, String collectionURI, String available, String returned) {
        this.items = items;
        this.collectionURI = collectionURI;
        this.available = available;
        this.returned = returned;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
}
