package marvel.br.com.lebronx.marvelcomics.Interface;

import java.util.List;

import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;

public interface CacheView {

    void setLast5CharactersCachedData(List<MarvelCharacter> marvelCharacterModels);

    void showError(Throwable throwable);
}
