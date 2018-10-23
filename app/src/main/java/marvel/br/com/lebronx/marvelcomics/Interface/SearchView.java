package marvel.br.com.lebronx.marvelcomics.Interface;

import java.util.List;

import marvel.br.com.lebronx.marvelcomics.Helper.ApiResponseCodeException;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;

public interface SearchView extends BaseView{
    void showQueryError(Throwable throwable);

    void showCharacter(MarvelCharacter character);

    void showRetryMessage(Throwable throwable);

    void showQueryNoResult();

    void setCharactersCachedData(List<MarvelCharacter> characters);

    void showError(Throwable throwable);

    void showProgress();

    void hideProgress();

    void showServiceError(ApiResponseCodeException throwable);

}
