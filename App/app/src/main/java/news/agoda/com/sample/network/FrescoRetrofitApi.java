package news.agoda.com.sample.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import news.agoda.com.sample.model.FrescoResponse;
import news.agoda.com.sample.model.MediaEntity;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by juanocampo on 1/26/18.
 */

public class FrescoRetrofitApi {

    private static FrescoRetrofitApi instance;
    private final FrescoApi frescoApi;

    public static FrescoRetrofitApi getInstance() {
        if (instance == null) {
            instance = new FrescoRetrofitApi();
        }
        return instance;
    }

    public FrescoRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        frescoApi = retrofit.create(FrescoApi.class);
    }

    public String getBaseUrl() {
        return "https://api.myjson.com";
    }

    public void getNewsFromService(Subscriber<? super FrescoResponse> subscriber) {
        try {
            Response<FrescoResponse> response = frescoApi.getNews().execute();
            if (response.isSuccessful() && response.body() != null) {
                subscriber.onNext(response.body());
            } else {
                subscriber.onError(new Exception("There was a problem with the request"));
            }

        } catch (IOException e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }


    public static List<MediaEntity> gsonToMediaEntry(Object object) {
        Gson gson = new Gson();
        final Type tokenMediaEntry = new TypeToken<List<MediaEntity>>() {
        }.getType();


        return gson.fromJson(object2StringGson(object), tokenMediaEntry);
    }

    public static String object2StringGson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
