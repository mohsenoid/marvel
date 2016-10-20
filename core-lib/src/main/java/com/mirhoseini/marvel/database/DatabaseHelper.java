package com.mirhoseini.marvel.database;

import com.mirhoseini.marvel.database.model.CharacterModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface DatabaseHelper {

    int addCharacter(CharacterModel character) throws SQLException;

    List<CharacterModel> selectLast5Characters() throws SQLException;

    List<CharacterModel> selectAllCharacters() throws SQLException;

}
