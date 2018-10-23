package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;
import android.support.v4.app.Fragment;

import marvel.br.com.lebronx.marvelcomics.MarvelApplication;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(MarvelApplication.get(getContext()));
    }

    protected abstract void injectDependencies(MarvelApplication marvelApplication);

    public abstract void showMessage(String message);

    public abstract void showOfflineMessage(boolean isCritical);
}
