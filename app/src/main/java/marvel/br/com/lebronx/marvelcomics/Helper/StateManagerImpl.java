package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;

import com.mirhoseini.utils.Utils;

import javax.inject.Inject;

import marvel.br.com.lebronx.marvelcomics.Interface.StateManager;

public class StateManagerImpl implements StateManager {

    private Context context;

    @Inject
    public StateManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isConnect() {
        return Utils.isConnected(context);
    }
}
