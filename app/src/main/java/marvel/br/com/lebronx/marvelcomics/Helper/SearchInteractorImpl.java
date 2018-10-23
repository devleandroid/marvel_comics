package marvel.br.com.lebronx.marvelcomics.Helper;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.SingleSubject;
import marvel.br.com.lebronx.marvelcomics.Interface.SchedulerProvider;
import marvel.br.com.lebronx.marvelcomics.Interface.ServiceMarvelApi;
import marvel.br.com.lebronx.marvelcomics.Manager.HashGenerator;
import marvel.br.com.lebronx.marvelcomics.Service.CharactersResponse;

class SearchInteractorImpl {
    private ServiceMarvelApi api;
    private SchedulerProvider scheduler;

    private SingleSubject<CharactersResponse> characterSubject;
    private Disposable characterSubscription;

    @Inject
    SearchInteractorImpl(ServiceMarvelApi api, SchedulerProvider scheduler) {
        this.api = api;
        this.scheduler = scheduler;
    }


    public Single<CharactersResponse> loadCharacter(String query,
                                                    String privateKey,
                                                    String publicKey,
                                                    long timestamp) {
        if (characterSubscription == null || characterSubscription.isDisposed()) {
            characterSubject = SingleSubject.create();

            // generate hash using timestamp and API keys
            String hash = HashGenerator.generate(timestamp, privateKey, publicKey);

            characterSubscription = api.getCharacters(query, publicKey, hash, timestamp)
                    .subscribeOn(scheduler.backgroundThread())
                    .subscribe(characterSubject::onSuccess);
        }

        return characterSubject.hide();
    }



    public void unbind() {
        if (characterSubscription != null && !characterSubscription.isDisposed())
            characterSubscription.dispose();
    }
}
