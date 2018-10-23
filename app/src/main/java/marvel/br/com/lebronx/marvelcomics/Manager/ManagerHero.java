package marvel.br.com.lebronx.marvelcomics.Manager;

import marvel.br.com.lebronx.marvelcomics.Interface.ManagerApi;
import marvel.br.com.lebronx.marvelcomics.Interface.OkCallBack;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import okhttp3.Request;

public class ManagerHero {

    private ManagerApi managerApi;

    private MarvelCharacter marvelCharacter;
    public ManagerHero(ManagerApi managerApi) {
        this.managerApi = managerApi;
    }

    public void listCharacters(Request request, OkCallBack okCallBack){

    }
}
