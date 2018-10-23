package marvel.br.com.lebronx.marvelcomics.Helper;

import android.content.Context;
import android.content.res.Resources;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Provider;

import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import marvel.br.com.lebronx.marvelcomics.Activity.ListHeroActivity;
import marvel.br.com.lebronx.marvelcomics.Activity.SplashActivity;
import marvel.br.com.lebronx.marvelcomics.AndroidModule;
import marvel.br.com.lebronx.marvelcomics.ApplicationModule;
import marvel.br.com.lebronx.marvelcomics.Interface.ApplicationComponent;
import marvel.br.com.lebronx.marvelcomics.Interface.CacheSubComponent;
import marvel.br.com.lebronx.marvelcomics.Interface.DatabaseHelper;
import marvel.br.com.lebronx.marvelcomics.Interface.SchedulerProvider;
import marvel.br.com.lebronx.marvelcomics.Interface.SearchSubComponent;
import marvel.br.com.lebronx.marvelcomics.Interface.ServiceMarvelApi;
import marvel.br.com.lebronx.marvelcomics.Interface.StateManager;
import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.Service.ApiModule;
import marvel.br.com.lebronx.marvelcomics.Service.ClientModule;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class DaggerApplicationComponent implements ApplicationComponent {
    private Provider<Context> provideContextProvider;

    private MembersInjector<SplashActivity> splashActivityMembersInjector;

    private MembersInjector<ListHeroActivity> mainActivityMembersInjector;

    private Provider<Resources> provideResourcesProvider;

    private Provider<FirebaseAnalytics> provideFirebaseAnalyticsProvider;

    private Provider<HttpUrl> provideEndpointProvider;

    private Provider<Gson> provideGsonProvider;

    private Provider<Converter.Factory> provideGsonConverterFactoryProvider;

    private Provider<CallAdapter.Factory> provideRxJavaCallAdapterFactoryProvider;

    private Provider<HttpLoggingInterceptor> provideHttpLoggingInterceptorProvider;

    private Provider<Integer> provideNetworkTimeoutInSecondsProvider;

    private Provider<Boolean> provideIsDebugProvider;

    private Provider<File> provideCacheDirProvider;

    private Provider<Long> provideCacheSizeProvider;

    private Provider<Cache> provideCacheProvider;

    private Provider<Integer> provideCacheMaxAgeMinutesProvider;

    private Provider<Interceptor> provideCacheInterceptorProvider;

    private Provider<StateManagerImpl> stateManagerImplProvider;

    private Provider<StateManager> provideStateManagerProvider;

    private Provider<Integer> provideCacheMaxStaleDaysProvider;

    private Provider<Interceptor> provideOfflineCacheInterceptorProvider;

    private Provider<Integer> provideApiRetryCountProvider;

    private Provider<Interceptor> provideRetryInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<ServiceMarvelApi> provideMarvelApiServiceProvider;

    private Provider<SchedulerProvider> provideAppSchedulerProvider;

    private Provider<DatabaseHelperImpl> databaseHelperImplProvider;

    private Provider<DatabaseHelper> provideDatabaseHelperServiceProvider;

    private DaggerApplicationComponent(Builder builder) {
        assert builder != null;
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Builder builder) {

        this.provideContextProvider =
                DoubleCheck.provider(AndroidModule_ProvideContextFactory.create(builder.androidModule));

        this.splashActivityMembersInjector =
                SplashActivity_MembersInjector.create(provideContextProvider);

        this.mainActivityMembersInjector = MainActivity_MembersInjector.create(provideContextProvider);

        this.provideResourcesProvider =
                DoubleCheck.provider(AndroidModule_ProvideResourcesFactory.create(builder.androidModule));

        this.provideFirebaseAnalyticsProvider =
                ApplicationModule_ProvideFirebaseAnalyticsFactory.create(
                        builder.applicationModule, provideContextProvider);

        this.provideEndpointProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideEndpointFactory.create(builder.applicationModule));

        this.provideGsonProvider =
                DoubleCheck.provider(ApiModule_ProvideGsonFactory.create(builder.apiModule));

        this.provideGsonConverterFactoryProvider =
                DoubleCheck.provider(
                        ApiModule_ProvideGsonConverterFactoryFactory.create(
                                builder.apiModule, provideGsonProvider));

        this.provideRxJavaCallAdapterFactoryProvider =
                DoubleCheck.provider(
                        ApiModule_ProvideRxJavaCallAdapterFactoryFactory.create(builder.apiModule));

        this.provideHttpLoggingInterceptorProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideHttpLoggingInterceptorFactory.create(builder.clientModule));

        this.provideNetworkTimeoutInSecondsProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideNetworkTimeoutInSecondsFactory.create(
                                builder.applicationModule));

        this.provideIsDebugProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideIsDebugFactory.create(builder.applicationModule));

        this.provideCacheDirProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideCacheDirFactory.create(
                                builder.applicationModule, provideContextProvider));

        this.provideCacheSizeProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideCacheSizeFactory.create(builder.applicationModule));

        this.provideCacheProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideCacheFactory.create(
                                builder.clientModule, provideCacheDirProvider, provideCacheSizeProvider));

        this.provideCacheMaxAgeMinutesProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideCacheMaxAgeMinutesFactory.create(builder.applicationModule));

        this.provideCacheInterceptorProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideCacheInterceptorFactory.create(
                                builder.clientModule, provideCacheMaxAgeMinutesProvider));

        this.stateManagerImplProvider = StateManagerImpl_Factory.create(provideContextProvider);

        this.provideStateManagerProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideStateManagerFactory.create(
                                builder.applicationModule, stateManagerImplProvider));

        this.provideCacheMaxStaleDaysProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideCacheMaxStaleDaysFactory.create(builder.applicationModule));

        this.provideOfflineCacheInterceptorProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideOfflineCacheInterceptorFactory.create(
                                builder.clientModule,
                                provideStateManagerProvider,
                                provideCacheMaxStaleDaysProvider));

        this.provideApiRetryCountProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideApiRetryCountFactory.create(builder.applicationModule));

        this.provideRetryInterceptorProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideRetryInterceptorFactory.create(
                                builder.clientModule, provideApiRetryCountProvider));

        this.provideOkHttpClientProvider =
                DoubleCheck.provider(
                        ClientModule_ProvideOkHttpClientFactory.create(
                                builder.clientModule,
                                provideHttpLoggingInterceptorProvider,
                                provideNetworkTimeoutInSecondsProvider,
                                provideIsDebugProvider,
                                provideCacheProvider,
                                provideCacheInterceptorProvider,
                                provideOfflineCacheInterceptorProvider,
                                provideRetryInterceptorProvider));

        this.provideRetrofitProvider =
                DoubleCheck.provider(
                        ApiModule_ProvideRetrofitFactory.create(
                                builder.apiModule,
                                provideEndpointProvider,
                                provideGsonConverterFactoryProvider,
                                provideRxJavaCallAdapterFactoryProvider,
                                provideOkHttpClientProvider));

        this.provideMarvelApiServiceProvider =
                DoubleCheck.provider(
                        ApiModule_ProvideMarvelApiServiceFactory.create(
                                builder.apiModule, provideRetrofitProvider));

        this.provideAppSchedulerProvider =
                DoubleCheck.provider(
                        ApplicationModule_ProvideAppSchedulerFactory.create(builder.applicationModule));

        this.databaseHelperImplProvider =
                DatabaseHelperImpl_Factory.create(
                        MembersInjectors.<DatabaseHelperImpl>noOp(), provideContextProvider);

        this.provideDatabaseHelperServiceProvider =
                DoubleCheck.provider(
                        DatabaseModule_ProvideDatabaseHelperServiceFactory.create(
                                builder.databaseModule, databaseHelperImplProvider));
    }

    @Override
    public void inject(SplashActivity activity) {
        splashActivityMembersInjector.injectMembers(activity);
    }

    @Override
    public void inject(ListHeroActivity activity) {
        mainActivityMembersInjector.injectMembers(activity);
    }

    @Override
    public void inject(MarvelCharacter characterActivity) {
        MembersInjectors.<CharacterActivity>noOp().injectMembers(characterActivity);
    }

    @Override
    public SearchSubComponent plus(SearchModule module) {
        return new SearchSubComponentImpl(module);
    }

    @Override
    public CacheSubComponent plus(CacheModule module) {
        return new CacheSubComponentImpl(module);
    }

    public static final class Builder {
        private AndroidModule androidModule;

        private ApplicationModule applicationModule;

        private ApiModule apiModule;

        private ClientModule clientModule;

        private DatabaseModule databaseModule;

        private Builder() {}

        public ApplicationComponent build() {
            if (androidModule == null) {
                throw new IllegalStateException(AndroidModule.class.getCanonicalName() + " must be set");
            }
            if (applicationModule == null) {
                this.applicationModule = new ApplicationModule();
            }
            if (apiModule == null) {
                this.apiModule = new ApiModule();
            }
            if (clientModule == null) {
                this.clientModule = new ClientModule();
            }
            if (databaseModule == null) {
                this.databaseModule = new DatabaseModule();
            }
            return new DaggerApplicationComponent(this);
        }

        public Builder androidModule(AndroidModule androidModule) {
            this.androidModule = Preconditions.checkNotNull(androidModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public Builder apiModule(ApiModule apiModule) {
            this.apiModule = Preconditions.checkNotNull(apiModule);
            return this;
        }

        public Builder databaseModule(DatabaseModule databaseModule) {
            this.databaseModule = Preconditions.checkNotNull(databaseModule);
            return this;
        }

        public Builder clientModule(ClientModule clientModule) {
            this.clientModule = Preconditions.checkNotNull(clientModule);
            return this;
        }
    }

    private final class SearchSubComponentImpl implements SearchSubComponent {
        private final SearchModule searchModule;

        @SuppressWarnings("rawtypes")
        private Provider searchInteractorImplProvider;

        @SuppressWarnings("rawtypes")
        private Provider provideInteractorProvider;

        @SuppressWarnings("rawtypes")
        private MembersInjector searchPresenterImplMembersInjector;

        @SuppressWarnings("rawtypes")
        private Provider searchPresenterImplProvider;

        @SuppressWarnings("rawtypes")
        private Provider providePresenterProvider;

        private MembersInjector<SearchFragment> searchFragmentMembersInjector;

        private SearchSubComponentImpl(SearchModule searchModule) {
            this.searchModule = Preconditions.checkNotNull(searchModule);
            initialize();
        }

        @SuppressWarnings("unchecked")
        private void initialize() {

            this.searchInteractorImplProvider =
                    DoubleCheck.provider(
                            SearchInteractorImpl_Factory.create(
                                    DaggerApplicationComponent.this.provideMarvelApiServiceProvider,
                                    DaggerApplicationComponent.this.provideAppSchedulerProvider));

            this.provideInteractorProvider =
                    DoubleCheck.provider(
                            SearchModule_ProvideInteractorFactory.create(
                                    searchModule, searchInteractorImplProvider));

            this.searchPresenterImplMembersInjector =
                    SearchPresenterImpl_MembersInjector.create(
                            provideInteractorProvider,
                            DaggerApplicationComponent.this.provideDatabaseHelperServiceProvider);

            this.searchPresenterImplProvider =
                    SearchPresenterImpl_Factory.create(
                            searchPresenterImplMembersInjector,
                            DaggerApplicationComponent.this.provideAppSchedulerProvider);

            this.providePresenterProvider =
                    DoubleCheck.provider(
                            SearchModule_ProvidePresenterFactory.create(
                                    searchModule, searchPresenterImplProvider));

            this.searchFragmentMembersInjector =
                    SearchFragment_MembersInjector.create(
                            DaggerApplicationComponent.this.provideContextProvider,
                            DaggerApplicationComponent.this.provideResourcesProvider,
                            DaggerApplicationComponent.this.provideFirebaseAnalyticsProvider,
                            providePresenterProvider);
        }

        @Override
        public void inject(SearchFragment fragment) {
            searchFragmentMembersInjector.injectMembers(fragment);
        }
    }

    private final class CacheSubComponentImpl implements CacheSubComponent {
        private final CacheModule cacheModule;

        @SuppressWarnings("rawtypes")
        private MembersInjector cachePresenterImplMembersInjector;

        @SuppressWarnings("rawtypes")
        private Provider cachePresenterImplProvider;

        @SuppressWarnings("rawtypes")
        private Provider providePresenterProvider;

        private Provider<CharactersRecyclerViewAdapter> charactersRecyclerViewAdapterProvider;

        private MembersInjector<CacheFragment> cacheFragmentMembersInjector;

        private CacheSubComponentImpl(CacheModule cacheModule) {
            this.cacheModule = Preconditions.checkNotNull(cacheModule);
            initialize();
        }

        @SuppressWarnings("unchecked")
        private void initialize() {

            this.cachePresenterImplMembersInjector =
                    CachePresenterImpl_MembersInjector.create(
                            DaggerApplicationComponent.this.provideDatabaseHelperServiceProvider);

            this.cachePresenterImplProvider =
                    CachePresenterImpl_Factory.create(cachePresenterImplMembersInjector);

            this.providePresenterProvider =
                    DoubleCheck.provider(
                            CacheModule_ProvidePresenterFactory.create(cacheModule, cachePresenterImplProvider));

            this.charactersRecyclerViewAdapterProvider =
                    CharactersRecyclerViewAdapter_Factory.create(
                            MembersInjectors.<CharactersRecyclerViewAdapter>noOp());

            this.cacheFragmentMembersInjector =
                    CacheFragment_MembersInjector.create(
                            DaggerApplicationComponent.this.provideContextProvider,
                            providePresenterProvider,
                            charactersRecyclerViewAdapterProvider);
        }

        @Override
        public void inject(CacheFragment fragment) {
            cacheFragmentMembersInjector.injectMembers(fragment);
        }
    }
}
