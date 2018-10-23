package marvel.br.com.lebronx.marvelcomics.Service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import marvel.br.com.lebronx.marvelcomics.Constants;
import marvel.br.com.lebronx.marvelcomics.Helper.Rest;
import marvel.br.com.lebronx.marvelcomics.Interface.OkCallBack;
import marvel.br.com.lebronx.marvelcomics.Interface.ServiceFailureCallback;
import okhttp3.Response;
import retrofit.Callback;


public class ServiceMarvel {

    protected Context context;
    public ServiceMarvel(Context ctx) {
        context = ctx;
    }
    long timeStamp = System.currentTimeMillis()/1000;

    public void getMarvelHero(final OkCallBack callback) {

        Rest.get( context,"characters?=1&apikey=" + Constants.PUBLIC_KEY, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response != null){
                        Gson gson = new GsonBuilder().create();
                        // callback.onResponse(gson.fromJson(response.toString(), Response.class));
                    } else {
                        String error = response.getString("");
                        callback.onError(error + " " + statusCode);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }
        });
    }


    public void getCharacter(final OkCallBack okCallBack) {
        Rest.get( context,"characters?=1&apikey=" + Constants.PUBLIC_KEY, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response != null){
                        Gson gson = new GsonBuilder().create();
                        okCallBack.onResponse(gson. <JSONArray>fromJson(response.toString(), JSONObject.class));
                    } else {
                        String error = response.getString("");
                        okCallBack.onError(error + " " + statusCode);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                okCallBack.onError(responseString);
            }
        });
    }
}
