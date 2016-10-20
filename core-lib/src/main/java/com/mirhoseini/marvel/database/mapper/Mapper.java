package com.mirhoseini.marvel.database.mapper;

import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.model.CharactersResponse;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Mapper {

    public static CharacterModel mapCharacterResponseToCharacter(CharactersResponse charactersResponse) {
        CharacterModel character = new CharacterModel();

        character.setName(charactersResponse.getData().getResults()[0].getName());
        character.setDescription(charactersResponse.getData().getResults()[0].getDescription());
        character.setThumbnail(String.format("%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));

        return character;
    }
}
