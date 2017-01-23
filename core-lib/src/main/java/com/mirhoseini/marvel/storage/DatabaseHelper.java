package com.mirhoseini.marvel.storage;

import com.mirhoseini.marvel.storage.model.CharacterModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface DatabaseHelper {

    int addCharacter(CharacterModel character) throws SQLException;

    List<CharacterModel> selectLast5Characters() throws SQLException;

    List<CharacterModel> selectAllCharacters() throws SQLException;

    CharacterModel getCharacters(String query) throws SQLException;

}
