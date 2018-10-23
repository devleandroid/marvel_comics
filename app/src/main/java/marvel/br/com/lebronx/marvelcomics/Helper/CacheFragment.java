package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import marvel.br.com.lebronx.marvelcomics.Constants;
import marvel.br.com.lebronx.marvelcomics.Interface.CachePresenter;
import marvel.br.com.lebronx.marvelcomics.Interface.CacheView;
import marvel.br.com.lebronx.marvelcomics.MarvelApplication;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.R;
import timber.log.Timber;

public class CacheFragment extends BaseFragment implements CacheView {

    public static final int COLUMN_COUNT = 2;
    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    CachePresenter presenter;
    @Inject
    CharactersRecyclerViewAdapter adapter;

    // injecting views via ButterKnife
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.empty)
    ViewGroup empty;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    GridLayoutManager gridLayoutManager;
    GridSpacingItemDecoration gridSpacingItemDecoration;

    PublishSubject<MarvelCharacter> notifyCharacter = PublishSubject.create();
    PublishSubject<String> notifyMessage = PublishSubject.create();
    PublishSubject<Boolean> notifyOffline = PublishSubject.create();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CacheFragment() {
    }

    public static CacheFragment newInstance() {
        CacheFragment fragment = new CacheFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter.bind(this);

        compositeDisposable.add(
                adapter.asObservable()
                        .subscribe(notifyCharacter::onNext));
    }

    @Override
    protected void injectDependencies(MarvelApplication application) {
        application
                .getCacheSubComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cache, container, false);

        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadLast5CharactersCachedData();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        presenter.unbind();

        compositeDisposable.dispose();
    }

    @Override
    public void showMessage(String message) {
        notifyMessage.onNext(message);
    }

    @Override
    public void showOfflineMessage(boolean isCritical) {
        notifyOffline.onNext(isCritical);
    }

    private void initRecyclerView() {
        initLayoutManager();
        initGridSpacingItemDecoration();

        list.setLayoutManager(gridLayoutManager);
        list.addItemDecoration(gridSpacingItemDecoration);
    }

    public void initLayoutManager() {
        gridLayoutManager = new GridLayoutManager(context, COLUMN_COUNT);
        // Create a custom SpanSizeLookup where the first item spans both columns
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? COLUMN_COUNT : 1;
            }
        });
    }

    public void initGridSpacingItemDecoration() {
        gridSpacingItemDecoration = new GridSpacingItemDecoration(COLUMN_COUNT, Constants.RECYCLER_VIEW_ITEM_SPACE, true, 1);
    }

    @Override
    public void setLast5CharactersCachedData(List<MarvelCharacter> marvelCharacterModels) {
        if (marvelCharacterModels.size() > 0) {
            list.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);

            adapter.setMarvelCharacters(marvelCharacterModels);
            list.setAdapter(adapter);
        } else {
            list.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    public Observable<MarvelCharacter> characterObservable() {
        return notifyCharacter.hide();
    }

    public Observable<String> messageObservable() {
        return notifyMessage.hide();
    }

    public Observable<Boolean> offlineObservable() {
        return notifyOffline.hide();
    }

}
