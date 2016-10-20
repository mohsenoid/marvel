package com.mirhoseini.marvel.database.model;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CharacterDao extends BaseDaoImpl<CharacterModel, Integer> {

    public CharacterDao(Class<CharacterModel> dataClass) throws SQLException {
        super(dataClass);
    }

    public CharacterDao(ConnectionSource connectionSource, Class<CharacterModel> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public CharacterDao(ConnectionSource connectionSource, DatabaseTableConfig<CharacterModel> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public List<CharacterModel> getCharacters() throws SQLException {
        return queryForAll();
    }

}
