package marvel.br.com.lebronx.marvelcomics.Interface;


import com.google.inject.Singleton;

import dagger.Component;
import marvel.br.com.lebronx.marvelcomics.Activity.CharacterActivity;
import marvel.br.com.lebronx.marvelcomics.Activity.ListHeroActivity;
import marvel.br.com.lebronx.marvelcomics.Activity.SplashActivity;
import marvel.br.com.lebronx.marvelcomics.AndroidModule;
import marvel.br.com.lebronx.marvelcomics.ApplicationModule;
import marvel.br.com.lebronx.marvelcomics.Helper.CacheModule;
import marvel.br.com.lebronx.marvelcomics.Helper.DatabaseModule;
import marvel.br.com.lebronx.marvelcomics.Helper.SearchModule;
import marvel.br.com.lebronx.marvelcomics.Service.ApiModule;
import marvel.br.com.lebronx.marvelcomics.Service.ClientModule;

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        DatabaseModule.class,
        ClientModule.class
})
public interface ApplicationComponent {

    void inject(SplashActivity activity);

    void inject(ListHeroActivity activity);

    void inject(CharacterActivity characterActivity);

    SearchSubComponent plus(SearchModule module);

    CacheSubComponent plus(CacheModule module);
}
