package marvel.br.com.lebronx.marvelcomics.Interface;

public interface ServiceFailureCallback {

    void onRetry();
    void onError(String error);
}
