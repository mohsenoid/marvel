package com.mirhoseini.marvel.database;


import com.mirhoseini.marvel.database.model.CharacterModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class DatabaseHelperImpl implements DatabaseHelper {


    @Override
    public int addCharacter(CharacterModel character) throws SQLException {
        return 0;
    }

    @Override
    public List<CharacterModel> selectLast5Characters() throws SQLException {
        return null;
    }

    @Override
    public List<CharacterModel> selectAllCharacters() throws SQLException {
        return null;
    }

    @Override
    public CharacterModel getCharacters(String query) throws SQLException {
        return null;
    }
}
