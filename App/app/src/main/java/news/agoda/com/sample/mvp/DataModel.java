package news.agoda.com.sample.mvp;

import news.agoda.com.sample.mvp.model.FrescoResponse;
import news.agoda.com.sample.network.FrescoRetrofitApi;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by juan.ocampo on 30/01/2018.
 */

public class DataModel {

    private FrescoRetrofitApi frescoRetrofitApi = FrescoRetrofitApi.getInstance();

    public Observable<FrescoResponse> getFrescoNewsObserver() {
        return Observable.create(new Observable.OnSubscribe<FrescoResponse>() {

            @Override
            public void call(Subscriber<? super FrescoResponse> subscriber) {
                frescoRetrofitApi.getNewsFromService(subscriber);
            }

        });
    }
}
