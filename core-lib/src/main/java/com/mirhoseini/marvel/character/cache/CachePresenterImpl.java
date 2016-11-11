package com.mirhoseini.marvel.character.cache;

import com.mirhoseini.marvel.database.DatabaseHelper;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by Mohsen on 20/10/2016.
 */

class CachePresenterImpl implements CachePresenter {

    @Inject
    DatabaseHelper databaseHelper;

    private CacheView view;

    @Inject
    public CachePresenterImpl() {
    }

    @Override
    public void bind(CacheView view) {
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
