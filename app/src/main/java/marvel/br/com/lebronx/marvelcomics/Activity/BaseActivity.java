package marvel.br.com.lebronx.marvelcomics.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import marvel.br.com.lebronx.marvelcomics.Interface.ApplicationComponent;
import marvel.br.com.lebronx.marvelcomics.MarvelApplication;

abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(MarvelApplication.get(this), MarvelApplication.getComponent());
    }

    protected abstract void injectDependencies(MarvelApplication application, ApplicationComponent component);

    protected abstract void releaseSubComponents(MarvelApplication application);
}
