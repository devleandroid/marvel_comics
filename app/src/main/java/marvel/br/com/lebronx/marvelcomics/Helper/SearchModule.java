package marvel.br.com.lebronx.marvelcomics.Helper;

import com.google.inject.Provides;

import marvel.br.com.lebronx.marvelcomics.Interface.Search;
import marvel.br.com.lebronx.marvelcomics.Interface.SearchInteractor;
import marvel.br.com.lebronx.marvelcomics.Interface.SearchPresenter;

public class SearchModule {
    @Provides
    @Search
    public SearchInteractor provideInteractor(SearchInteractorImpl interactor) {
        return (SearchInteractor) interactor;
    }

    @Provides
    @Search
    public SearchPresenter providePresenter(SearchPresenterImpl presenter) {
        return (SearchPresenter) presenter;
    }

}
