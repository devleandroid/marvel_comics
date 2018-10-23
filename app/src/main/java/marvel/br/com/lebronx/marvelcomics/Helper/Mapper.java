package marvel.br.com.lebronx.marvelcomics.Helper;

import marvel.br.com.lebronx.marvelcomics.Constants;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.Service.CharactersResponse;

public class Mapper {
    public static MarvelCharacter mapCharacterResponseToCharacter(CharactersResponse charactersResponse) {
        MarvelCharacter mCharacter = new MarvelCharacter();

        mCharacter.setName(charactersResponse.getData().getResults()[0].getName());
        mCharacter.setDescription(charactersResponse.getData().getResults()[0].getDescription());
        mCharacter.setThumbnail(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.STANDARD_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));
        mCharacter.setImage(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.PORTRAIT_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));

        return mCharacter;
    }
}
