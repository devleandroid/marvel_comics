package marvel.br.com.lebronx.marvelcomics.Interface;


import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Query;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ManagerApi {

    @GET("/v1/public/characters")
    public void listCharacters(@Query("limit") int limit
            , @Query("offset") int offset
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature
            , @Query("name") String name
            , @Query("modifiedSince") Date modifiedSince
            , @Query("comics") String comics
            , @Query("series") String series
            , @Query("events") String events
            , @Query("orderBy") String orderBy
            , Callback<Response<Character>> callback);
}
