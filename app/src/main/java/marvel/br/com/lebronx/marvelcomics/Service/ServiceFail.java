package marvel.br.com.lebronx.marvelcomics.Service;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

import marvel.br.com.lebronx.marvelcomics.Interface.ServiceFailureCallback;

public class ServiceFail {

    static public int retry = 0;

    static public void getFailure(final Context context, URI uri, Throwable throwable, int statusCode, JSONObject errorResponse, final ServiceFailureCallback callback){
        if(statusCode == -900){ //falha de internet
            callback.onError("Para realizar esta ação você precisa estar conectado a internet. Verifique sua conexão e tente novamente.");
        }else{
            try {
                JSONObject jsonResponse = (JSONObject)errorResponse;

                String strURI ="";
                if(uri != null){
                    strURI =  uri.toString();
                }
                if (jsonResponse != null) {
                    if(retry == 0){
                        retry = 1;
                    }else{
                        retry = 0;
                        callback.onError("Desculpe o transtorno, por gentileza tente novamente.");
                    }
                } else{
                    apiError(jsonResponse,callback);
                }
                    String strError = "Desculpe o transtorno, por gentileza tente novamente.";
                    if(jsonResponse.getString("Mensagem") != null){
                        strError = jsonResponse.getString("Mensagem");
                    }
                callback.onError(strError);
                
            } catch (JSONException e){
                callback.onError("Desculpe o transtorno, por gentileza tente novamente.");
            }
        }
    }

    private static void apiError(JSONObject jsonResponse, ServiceFailureCallback callback) {
        String msg = "Desculpe o transtorno, por gentileza tente novamente.";
        try {
            if (jsonResponse != null) {
                msg = jsonResponse.getString("Mensagem");
                callback.onError(msg);
            }
        } catch (JSONException e) {
            callback.onError(msg);
        }
    }

}
