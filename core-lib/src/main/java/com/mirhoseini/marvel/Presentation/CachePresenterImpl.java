package com.mirhoseini.marvel.Presentation;

import com.mirhoseini.marvel.database.DatabaseHelper;
import com.mirhoseini.marvel.view.CacheView;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CachePresenterImpl implements CachePresenter {

    @Inject
    DatabaseHelper databaseHelper;

    private CacheView view;

    @Inject
    public CachePresenterImpl() {
    }

    @Override
    public void setView(CacheView view) {
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
    public void destroy() {
        view = null;
    }
}
