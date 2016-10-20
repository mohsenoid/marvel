package com.mirhoseini.marvel.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mohsen on 20/10/2016.
 */

@DatabaseTable(tableName = CharacterModel.TABLE_NAME_CHARACTER, daoClass = CharacterDao.class)
public class CharacterModel implements Serializable {
    public static final String TABLE_NAME_CHARACTER = "characters";
    public static final String FIELD_CHARACTER_ID = "_id";
    public static final String FIELD_CHARACTER_NAME = "name";
    public static final String FIELD_CHARACTER_DESCRIPTION = "description";
    public static final String FIELD_CHARACTER_THUMBNAIL = "thumbnail";

    @DatabaseField(generatedId = true, columnName = FIELD_CHARACTER_ID)
    private int id;
    @DatabaseField(columnName = FIELD_CHARACTER_NAME)
    private String name;
    @DatabaseField(columnName = FIELD_CHARACTER_DESCRIPTION)
    private String description;
    @DatabaseField(columnName = FIELD_CHARACTER_THUMBNAIL)
    private String thumbnail;

    public CharacterModel() {
    }

    public CharacterModel(int id, String name, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return name;
    }
}