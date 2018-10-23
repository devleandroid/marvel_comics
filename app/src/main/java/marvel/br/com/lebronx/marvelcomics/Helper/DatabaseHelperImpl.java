package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import marvel.br.com.lebronx.marvelcomics.Interface.DatabaseHelper;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.R;

class DatabaseHelperImpl extends OrmLiteSqliteOpenHelper implements DatabaseHelper {
    private static final String DATABASE_NAME = "marvel.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<MarvelCharacter, Integer> characterDao;

    @Inject
    public DatabaseHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, MarvelCharacter.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, MarvelCharacter.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<MarvelCharacter, Integer> getCharacterDao() throws SQLException {
        if (characterDao == null) {
            characterDao = getDao(MarvelCharacter.class);
        }
        return characterDao;
    }

    @Override
    public int addCharacter(MarvelCharacter marvelCharacter) throws SQLException {
        MarvelCharacter result = getCharacterDao().queryForFirst(getCharacterDao()
                .queryBuilder()
                .where()
                .like(MarvelCharacter.FIELD_CHARACTER_NAME, marvelCharacter.getName())
                .prepare());

        if (null != result)
            getCharacterDao().delete(result);

        return getCharacterDao().create(marvelCharacter);
    }

    @Override
    public List<MarvelCharacter> selectLast5Characters() throws SQLException {
        return getCharacterDao().query(getCharacterDao().queryBuilder().orderBy(MarvelCharacter.FIELD_CHARACTER_ID, false).limit(5L).prepare());
    }

    @Override
    public List <MarvelCharacter> selectAllCharacters() throws SQLException {
        return getCharacterDao().query(getCharacterDao().queryBuilder().prepare());
    }

    // Close the database connections and clear any cached DAOs.
    @Override
    public void close() {
        super.close();
        characterDao = null;
    }
}
