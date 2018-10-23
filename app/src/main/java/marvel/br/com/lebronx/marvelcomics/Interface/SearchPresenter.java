package marvel.br.com.lebronx.marvelcomics.Interface;

public interface SearchPresenter {
    void bind(SearchView view);

    void doSearch(boolean isConnected, String query, long timestamp);

    boolean isQueryValid(String query);

    void loadCharactersCachedData();

    void unbind();
}
