package news.agoda.com.sample.network;

import news.agoda.com.sample.mvp.model.FrescoResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juanocampo on 1/26/18.
 */

public interface FrescoApi {

    @GET("bins/nl6jh")
    Call<FrescoResponse> getNews();

}
