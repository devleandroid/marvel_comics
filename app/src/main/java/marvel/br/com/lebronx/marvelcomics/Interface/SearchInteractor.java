package marvel.br.com.lebronx.marvelcomics.Interface;

import io.reactivex.Single;
import marvel.br.com.lebronx.marvelcomics.Service.CharactersResponse;

public interface SearchInteractor extends BaseInteractor{
    Single<CharactersResponse> loadCharacter(String query, String privateKey, String publicKey, long timestamp);
}
