package marvel.br.com.lebronx.marvelcomics.Interface;

import java.sql.SQLException;
import java.util.List;

import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;

public interface DatabaseHelper {

    int addCharacter(MarvelCharacter marvelCharacter) throws SQLException;

    List<MarvelCharacter> selectLast5Characters() throws SQLException;

    List <MarvelCharacter> selectAllCharacters() throws SQLException;
}
