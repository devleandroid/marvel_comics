package marvel.br.com.lebronx.marvelcomics.Interface;

interface BasePresenter<T> {
    void bind(T view);

    void unbind();
}
