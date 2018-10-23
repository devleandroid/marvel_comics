package marvel.br.com.lebronx.marvelcomics.Helper;

import com.google.inject.Provides;

import javax.inject.Singleton;

import marvel.br.com.lebronx.marvelcomics.Interface.DatabaseHelper;

public class DatabaseModule {
    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelperService(DatabaseHelperImpl databaseHelper) {
        return databaseHelper;
    }
}
