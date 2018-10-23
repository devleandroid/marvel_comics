package marvel.br.com.lebronx.marvelcomics.Helper;

import com.google.inject.Provides;

import marvel.br.com.lebronx.marvelcomics.Interface.Cache;
import marvel.br.com.lebronx.marvelcomics.Interface.CachePresenter;
import marvel.br.com.lebronx.marvelcomics.Manager.CachePresenterImpl;

public class CacheModule {
    @Provides
    @Cache
    public CachePresenter providePresenter(CachePresenterImpl presenter) {
        return presenter;
    }
}
