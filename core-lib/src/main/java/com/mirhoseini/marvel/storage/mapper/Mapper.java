package com.mirhoseini.marvel.storage.mapper;

import com.mirhoseini.marvel.storage.model.CharacterModel;
import com.mirhoseini.marvel.network.model.CharactersResponse;
import com.mirhoseini.marvel.util.Constants;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Mapper {

    public static CharacterModel mapCharacterResponseToCharacter(CharactersResponse charactersResponse) {
        CharacterModel character = new CharacterModel();

        character.setName(charactersResponse.getData().getResults()[0].getName());
        character.setDescription(charactersResponse.getData().getResults()[0].getDescription());
        character.setThumbnail(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.STANDARD_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));
        character.setImage(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.PORTRAIT_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));

        return character;
    }
}
