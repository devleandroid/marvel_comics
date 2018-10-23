package marvel.br.com.lebronx.marvelcomics;

import android.content.Context;
import android.content.res.Resources;

import com.google.inject.Provides;

import javax.inject.Singleton;

public class AndroidModule {

    private MarvelApplication application;

    public AndroidModule(MarvelApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}
