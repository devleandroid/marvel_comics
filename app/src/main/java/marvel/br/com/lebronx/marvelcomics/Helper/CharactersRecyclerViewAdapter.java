package marvel.br.com.lebronx.marvelcomics.Helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.R;


public class CharactersRecyclerViewAdapter  extends RecyclerView.Adapter<CharacterViewHolder>{

    private ArrayList<MarvelCharacter> marvelCharacters = new ArrayList<>();

    private PublishSubject<MarvelCharacter> notify = PublishSubject.create();

    @Inject
    public CharactersRecyclerViewAdapter() {
    }

    public void setMarvelCharacters(List<MarvelCharacter> marvelCharacterModels) {
        this.marvelCharacters = new ArrayList<>(marvelCharacters);
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CharacterViewHolder holder, int position) {

        final MarvelCharacter marvelCharacter = marvelCharacters.get(position);

        holder.setMarvelCharacter(marvelCharacter);

        RxView.clicks(holder.view)
                .map(aVoid -> holder.getMarvelCharacter())
                .subscribe(notify::onNext);
    }

    public Observable<MarvelCharacter> asObservable() {
        return notify.hide();
    }

    @Override
    public int getItemCount() {
        return marvelCharacters.size();
    }

}
