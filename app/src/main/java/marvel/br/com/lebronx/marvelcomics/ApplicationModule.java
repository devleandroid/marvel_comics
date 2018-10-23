package marvel.br.com.lebronx.marvelcomics;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.inject.Provides;
import com.mirhoseini.utils.Utils;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import marvel.br.com.lebronx.marvelcomics.Helper.AppSchedulerProvider;
import marvel.br.com.lebronx.marvelcomics.Helper.StateManagerImpl;
import marvel.br.com.lebronx.marvelcomics.Interface.SchedulerProvider;
import marvel.br.com.lebronx.marvelcomics.Interface.StateManager;
import okhttp3.HttpUrl;

public class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    int provideNetworkTimeoutInSeconds() {
        return Constants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(Constants.BASE_URL);
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    long provideCacheSize() {
        return Constants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return Constants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return Constants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("retryCount")
    public int provideApiRetryCount() {
        return Constants.API_RETRY_COUNT;
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    File provideCacheDir(Context context) {
        return context.getCacheDir();
    }

    @Provides
    @Named("isConnect")
    boolean provideIsConnect(Context context) {
        return Utils.isConnected(context);
    }

    @Provides
    FirebaseAnalytics provideFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @Singleton
    public StateManager provideStateManager(StateManagerImpl stateManager) {
        return stateManager;
    }
}
