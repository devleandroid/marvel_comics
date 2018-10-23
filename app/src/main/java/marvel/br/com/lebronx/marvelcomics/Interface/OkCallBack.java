package marvel.br.com.lebronx.marvelcomics.Interface;

import org.json.JSONArray;

import okhttp3.Response;
import retrofit.http.GET;

public interface OkCallBack {
    void onError(String error);
    void onResponse(JSONArray response);

}
