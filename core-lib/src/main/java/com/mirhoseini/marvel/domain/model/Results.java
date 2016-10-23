package com.mirhoseini.marvel.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Results {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("resourceURI")
    private String resourceURI;
    @SerializedName("modified")
    private String modified;
    @SerializedName("urls")
    private Urls[] urls;
    @SerializedName("series")
    private Collection series;
    @SerializedName("stories")
    private Collection stories;
    @SerializedName("events")
    private Collection events;
    @SerializedName("comics")
    private Collection comics;

    public Results() {
    }

    public Results(String id, String name, String description, Thumbnail thumbnail, String resourceURI, String modified, Urls[] urls, Collection series, Collection stories, Collection events, Collection comics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.resourceURI = resourceURI;
        this.modified = modified;
        this.urls = urls;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.comics = comics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Urls[] getUrls() {
        return urls;
    }

    public void setUrls(Urls[] urls) {
        this.urls = urls;
    }

    public Collection getSeries() {
        return series;
    }

    public void setSeries(Collection series) {
        this.series = series;
    }

    public Collection getStories() {
        return stories;
    }

    public void setStories(Collection stories) {
        this.stories = stories;
    }

    public Collection getEvents() {
        return events;
    }

    public void setEvents(Collection events) {
        this.events = events;
    }

    public Collection getComics() {
        return comics;
    }

    public void setComics(Collection comics) {
        this.comics = comics;
    }
}
