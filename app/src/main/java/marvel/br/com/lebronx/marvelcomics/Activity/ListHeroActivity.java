package marvel.br.com.lebronx.marvelcomics.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import marvel.br.com.lebronx.marvelcomics.Adapter.CharacterListAdapter;
import marvel.br.com.lebronx.marvelcomics.Interface.ManagerApi;
import marvel.br.com.lebronx.marvelcomics.Interface.OkCallBack;
import marvel.br.com.lebronx.marvelcomics.R;
import marvel.br.com.lebronx.marvelcomics.Service.ServiceMarvel;
import okhttp3.Response;
import retrofit.Callback;
import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Header;

public class ListHeroActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHttp;
    ProgressBar loader;
    CoordinatorLayout coordinatorLayout;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hero);
        loadComponents();
    }

    private void loadComponents() {

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        loader = (ProgressBar) findViewById (R.id.loader);

        process();
        // listHero();

    }

    @Override
    public void onClick(View v) {

    }



    private void process() {
        loader.setVisibility(View.VISIBLE);
        final ServiceMarvel service = new ServiceMarvel(this);
        service.getCharacter(new OkCallBack() {

            @Override
            public void onError(String error) {
                loader.setVisibility(View.GONE);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    ListView lv = (ListView) findViewById(R.id.listView);
                    lv.setAdapter(new CharacterListAdapter(ListHeroActivity.this,response));
                }
            }
        });
    }
}
