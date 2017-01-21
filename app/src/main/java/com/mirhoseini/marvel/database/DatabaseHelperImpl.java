package com.mirhoseini.marvel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class DatabaseHelperImpl extends OrmLiteSqliteOpenHelper implements DatabaseHelper {

    private static final String DATABASE_NAME = "marvel.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<CharacterModel, Integer> characterDao;

    @Inject
    public DatabaseHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, CharacterModel.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, CharacterModel.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<CharacterModel, Integer> getCharacterDao() throws SQLException {
        if (characterDao == null) {
            characterDao = getDao(CharacterModel.class);
        }
        return characterDao;
    }

    @Override
    public int addCharacter(CharacterModel character) throws SQLException {
        CharacterModel result = getCharacterDao().queryForFirst(getCharacterDao()
                .queryBuilder()
                .where()
                .like(CharacterModel.FIELD_CHARACTER_NAME, character.getName())
                .prepare());

        if (null != result)
            getCharacterDao().delete(result);

        return getCharacterDao().create(character);
    }

    @Override
    public List<CharacterModel> selectLast5Characters() throws SQLException {
        return getCharacterDao().query(getCharacterDao()
                .queryBuilder()
                .orderBy(CharacterModel.FIELD_CHARACTER_ID, false)
                .limit(5L)
                .prepare());
    }

    @Override
    public List<CharacterModel> selectAllCharacters() throws SQLException {
        return getCharacterDao().query(getCharacterDao().queryBuilder()
                .prepare());
    }

    @Override
    public CharacterModel getCharacters(String query) throws SQLException {
        return getCharacterDao().queryForFirst(getCharacterDao()
                .queryBuilder()
                .where()
                .like(CharacterModel.FIELD_CHARACTER_NAME, query)
                .prepare());
    }

    // Close the database connections and clear any cached DAOs.
    @Override
    public void close() {
        super.close();
        characterDao = null;
    }
}
