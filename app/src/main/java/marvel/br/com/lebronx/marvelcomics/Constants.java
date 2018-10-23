package marvel.br.com.lebronx.marvelcomics;

public class Constants {

    public static final String URL = "https://gateway.marvel.com/v1/public/";

    public static final String BASE_URL = URL;

    public static final String PUBLIC_KEY = "31226e4da8fb0b6350c9a63f162e5f22";

    public static final String PRIVATE_KEY = "85eb60b05749df1ae83e014358b4740ef5add78a";

    public static final int SPLASH_TIMEOUT_SEC = 3 * 1000; //3 sec

    public static final int RECYCLER_VIEW_ITEM_SPACE = 48;

    public static final int API_RETRY_COUNT = 3;

    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day

    public static final int CODE_OK = 200;

    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String STANDARD_XLARGE = "standard_xlarge";
}
