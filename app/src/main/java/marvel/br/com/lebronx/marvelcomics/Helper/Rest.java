package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import marvel.br.com.lebronx.marvelcomics.Constants;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;

public class Rest {

    private MarvelCharacter marvelCharacter;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(final Context ctx, final String url, final RequestParams params, final TextHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.BASE_URL + relativeUrl;
    }
}
