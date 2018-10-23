package marvel.br.com.lebronx.marvelcomics.Manager;

import java.sql.SQLException;

import javax.inject.Inject;

import marvel.br.com.lebronx.marvelcomics.Interface.CachePresenter;
import marvel.br.com.lebronx.marvelcomics.Interface.CacheView;
import marvel.br.com.lebronx.marvelcomics.Interface.DatabaseHelper;

public class CachePresenterImpl implements CachePresenter {
    @Inject
    DatabaseHelper databaseHelper;

    private CacheView view;

    @Inject
    public CachePresenterImpl() {
    }

    @Override
    public void bind(CacheView view) {
        this.view = view;
    }

    @Override
    public void loadLast5CharactersCachedData() {
        if (null != view)
            try {
                view.setLast5CharactersCachedData(databaseHelper.selectLast5Characters());
            } catch (SQLException e) {
                view.showError(e);
            }
    }

    @Override
    public void unbind() {
        view = null;
    }
}
