package marvel.br.com.lebronx.marvelcomics.Interface;

import io.reactivex.Single;
import marvel.br.com.lebronx.marvelcomics.Service.CharactersResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceMarvelApi {

    String NAME = "name";
    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";

    @GET("v1/public/characters")
    Single<CharactersResponse> getCharacters(
            @Query(NAME) String query,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);
}
