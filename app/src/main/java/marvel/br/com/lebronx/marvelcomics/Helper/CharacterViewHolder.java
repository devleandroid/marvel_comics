package marvel.br.com.lebronx.marvelcomics.Helper;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import marvel.br.com.lebronx.marvelcomics.BR;

import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;


public class CharacterViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    private MarvelCharacter marvelCharacter;
    private ViewDataBinding binding;

    public CharacterViewHolder(View view) {
        super(view);
        this.view = view;

        binding = DataBindingUtil.bind(view);

    }

    public MarvelCharacter getMarvelCharacter() {
        return marvelCharacter;
    }

    public void setMarvelCharacter(MarvelCharacter marvelCharacter) {
        this.marvelCharacter = marvelCharacter;

        binding.setVariable(BR.character, marvelCharacter);
        binding.executePendingBindings();
    }

}
