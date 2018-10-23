package marvel.br.com.lebronx.marvelcomics.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import marvel.br.com.lebronx.marvelcomics.Interface.ApplicationComponent;
import marvel.br.com.lebronx.marvelcomics.MarvelApplication;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.R;
import timber.log.Timber;

public class CharacterActivity extends BaseActivity {

    private static final String ARG_CHARACTER = "characterModel";

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context, Character character) {
        Intent intent = new Intent(context, CharacterActivity.class);
        intent.putExtra(ARG_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_character);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        if (null == getIntent() || null == getIntent().getExtras() || null == getIntent().getExtras().getSerializable(ARG_CHARACTER)) {
            finish();
            return;
        }

        // get args
        MarvelCharacter character = (MarvelCharacter) getIntent().getExtras().getSerializable(ARG_CHARACTER);

        // bind value using Android Binding
        // binding.setVariable(BR.character, character);

        setupToolbar(character.getName());

        Timber.d("MarvelCharacter Activity Created");
    }

    @Override
    protected void injectDependencies(MarvelApplication application, ApplicationComponent component) {
        component.inject(this);
    }

    private void setupToolbar(String characterName) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(characterName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void releaseSubComponents(MarvelApplication application) {

    }
}
