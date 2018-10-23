package marvel.br.com.lebronx.marvelcomics.Interface;

import marvel.br.com.lebronx.marvelcomics.Helper.SearchFragment;
import marvel.br.com.lebronx.marvelcomics.Helper.SearchModule;
import dagger.Subcomponent;

@Search
@Subcomponent(modules = {
        SearchModule.class
})
public interface SearchSubComponent {
    void inject(SearchFragment fragment);
}
