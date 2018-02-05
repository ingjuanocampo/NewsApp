package news.agoda.com.sample.mvp;

import news.agoda.com.sample.mvp.model.FrescoResponse;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by juanocampo on 1/26/18.
 */

public class NewsPresenter implements BaseViewBinder {

    private final NewsView newsView;
    private final DataModel dataModel;
    private final Scheduler schedulerObserve;
    private final Scheduler schedulerSubscriber;
    private Subscription subscription;
    private FrescoResponse frescoResponse;


    public NewsPresenter(NewsView newsView, DataModel dataModel) {
        this.newsView = newsView;
        this.dataModel = dataModel;
        this.schedulerObserve = AndroidSchedulers.mainThread();
        this.schedulerSubscriber = Schedulers.newThread();
    }

    public NewsPresenter(NewsView newsView, DataModel dataModel, Scheduler schedulerObserve, Scheduler schedulerSubscriber) {
        this.newsView = newsView;
        this.dataModel = dataModel;
        this.schedulerObserve = schedulerObserve;
        this.schedulerSubscriber = schedulerSubscriber;
    }

    @Override
    public void bind() {
        if (frescoResponse == null) {
            newsView.showLoading(true);
            subscription = dataModel.getFrescoNewsObserver()
                    .subscribeOn(schedulerSubscriber)
                    .observeOn(schedulerObserve)
                    .subscribe(getSubscribe());
        }
    }

    public Observer<? super FrescoResponse> getSubscribe() {
        return new Subscriber<FrescoResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                newsView.showLoading(false);
                newsView.setError();
            }

            @Override
            public void onNext(FrescoResponse frescoResponse) {
                newsView.showLoading(false);
                NewsPresenter.this.frescoResponse = frescoResponse;
                newsView.setNews(frescoResponse.getNewsEntities());
            }
        };
    }

    @Override
    public void unBind() {
        subscription.unsubscribe();
    }

}
