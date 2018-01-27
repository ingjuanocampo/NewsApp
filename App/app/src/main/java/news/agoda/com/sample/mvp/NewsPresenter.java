package news.agoda.com.sample.mvp;

import news.agoda.com.sample.model.FrescoResponse;
import news.agoda.com.sample.network.FrescoRetrofitApi;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by juanocampo on 1/26/18.
 */

public class NewsPresenter implements BaseViewBinder {

    private final NewsView newsView;
    private Subscription subscription;
    private FrescoRetrofitApi frescoRetrofitApi = FrescoRetrofitApi.getInstance();

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }


    @Override
    public void bind() {
        subscription = Observable.create(new Observable.OnSubscribe<FrescoResponse>() {

            @Override
            public void call(Subscriber<? super FrescoResponse> subscriber) {
                frescoRetrofitApi.getNewsFromService(subscriber);

            }

        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscribe());
    }

    private Observer<? super FrescoResponse> getSubscribe() {
        return new Subscriber<FrescoResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                newsView.setError();
            }

            @Override
            public void onNext(FrescoResponse newsEntity) {
                newsView.setNews(newsEntity.getNewsEntities());
            }
        };
    }

    @Override
    public void unBind() {
        subscription.unsubscribe();
    }

}
