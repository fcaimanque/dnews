package cl.ucn.disc.dsm.fcaimanque.dnews.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dsm.fcaimanque.dnews.model.Article;
import cl.ucn.disc.dsm.fcaimanque.dnews.model.NewsAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 *
 */
public final class NewsController {

    /**
     *
     */
    public interface NewsService {
        @Headers({"X-Api-Key: 408ccbe1e91345cc8232dc2115645cfb"})
        @GET("top-headlines?sources=cnn-es")
        Call<NewsAPI> getTopHeadlines();
    }

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    /**
     * Interceptor
     */
    private static final HttpLoggingInterceptor interceptor;

    static {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    /**
     * The Client
     */
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();



    private static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    private static final NewsService newsService = retrofit.create(NewsService.class);

    /**
     *
     * @return Listado de Articulos
     * @throws IOException
     */
    public static List<Article> getArticles() throws IOException {
        Call<NewsAPI> callNews = newsService.getTopHeadlines();

        NewsAPI news = callNews.execute().body();
        if (news == null){
            return null;
        }
        return news.getArticles();
    }

//TODO: libreria de fecha hace dias
}
