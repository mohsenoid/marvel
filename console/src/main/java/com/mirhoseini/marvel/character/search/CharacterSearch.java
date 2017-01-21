package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.ConsoleComponent;
import com.mirhoseini.marvel.database.model.CharacterModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mohsen on 28/11/2016.
 */

public class CharacterSearch implements SearchContract.View {
    @Inject
    SearchContract.Presenter presenter;

    public CharacterSearch(ConsoleComponent component) {
        component.plus(new SearchModule())
                .inject(this);

        presenter.bind(this);
    }

    public void doSearch(String query) {
        presenter.doSearch(true, query, System.currentTimeMillis() / 1000);
    }

    @Override
    public void showQueryError(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    public void showCharacter(CharacterModel character) {
        System.out.println(String.format("----------\n%s:\n%s\n----------", character.getName(), character.getDescription()));
    }

    @Override
    public void showRetryMessage(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    public void showQueryNoResult() {
        System.out.println("No result!!");
    }

    @Override
    public void setCharactersCachedData(List<CharacterModel> characters) {

    }

    @Override
    public void showError(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    public void showDatabaseError(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    public void showProgress() {
        System.out.println("Loading...");
    }

    @Override
    public void hideProgress() {
        System.out.println("Done!");
    }

    @Override
    public void showServiceError(ApiResponseCodeException throwable) {
        System.out.println(throwable);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showOfflineMessage(boolean isCritical) {
        System.out.println("Offline!!!");
    }
}
