package com.mirhoseini.marvel.character.cache;

import com.mirhoseini.marvel.database.DatabaseHelper;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Listens to user actions from the UI ({@link CacheContract.View}), retrieves the data and updates
 * the UI as required.
 */
class CachePresenter implements CacheContract.Presenter {

    DatabaseHelper databaseHelper;

    private CacheContract.View view;

    @Inject
    public CachePresenter(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void bind(CacheContract.View view) {
        this.view = view;
    }

    @Override
    public void loadLast5CharactersCachedData() {
        if (null != view)
            try {
                view.setLast5CharactersCachedData(databaseHelper.selectLast5Characters());
            } catch (SQLException e) {
                view.showError(e);
            }
    }

    @Override
    public void unbind() {
        view = null;
    }
}
