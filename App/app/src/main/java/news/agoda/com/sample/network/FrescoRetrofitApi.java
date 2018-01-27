package news.agoda.com.sample.network;

import java.io.IOException;

import news.agoda.com.sample.model.NewsEntity;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by juanocampo on 1/26/18.
 */

public class FrescoRetrofitApi {

    private static FrescoRetrofitApi insntace;
    private final FrescoApi frescoApi;

    public static FrescoRetrofitApi getInstance() {
        if (insntace == null) {
            insntace = new FrescoRetrofitApi();
        }
        return insntace;
    }

    public FrescoRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        frescoApi = retrofit.create(FrescoApi.class);
    }

    public FrescoApi getApi() {
        return frescoApi;
    }


    public String getBaseUrl() {
        return "https://api.myjson.com";
    }

    public void getNewsFromService(Subscriber<? super NewsEntity> subscriber) {
        try {
            Response<NewsEntity> response = frescoApi.getNews().execute();
            if (response.isSuccessful()) {
                subscriber.onNext(response.body());
            } else  {
                subscriber.onError(new Exception("There was a problem with the request"));
            }

        } catch (IOException e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }
}
