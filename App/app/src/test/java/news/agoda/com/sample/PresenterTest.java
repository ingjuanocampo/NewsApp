package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.mvp.DataModel;
import news.agoda.com.sample.mvp.NewsPresenter;
import news.agoda.com.sample.mvp.NewsView;
import news.agoda.com.sample.mvp.model.FrescoResponse;
import news.agoda.com.sample.mvp.model.NewsEntity;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by juan.ocampo on 30/01/2018.
 */
@RunWith(RobolectricTestRunner.class)
public class PresenterTest {

    @Mock
    DataModel dataModel;

    @Mock
    NewsView view;

    @Mock
    FrescoResponse frescoResponse;

    @Mock
    NewsEntity entity;

    private NewsPresenter newsPresenter;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        entity = mock(NewsEntity.class);
        List<NewsEntity> entities = new ArrayList<>();
        entities.add(entity);

        when(frescoResponse.getNewsEntities()).thenReturn(entities);

        when(entity.getTitle()).thenReturn("Title of news");
        when(entity.getImageUrl()).thenReturn("https://scontent.cdninstagram.com/t51.2885-19/s150x150/17493755_1303256426427937_8085166666457022464_a.jpg");
        testScheduler = new TestScheduler();

        newsPresenter = new NewsPresenter(view, dataModel, testScheduler, testScheduler);

    }

    @Test
    public void testSetNewsEntity() {
        Observable<FrescoResponse> observable = Observable.just(frescoResponse);
        doReturn(observable).when(dataModel).getFrescoNewsObserver();

        newsPresenter.bind();
        verify(view).showLoading(true);
        testScheduler.triggerActions();
        verify(view).showLoading(false);
        verify(view).setNews(frescoResponse.getNewsEntities());
    }

    @Test
    public void testErrorEntity() {
        Observable<FrescoResponse> observable = Observable.error(new Exception());
        doReturn(observable).when(dataModel).getFrescoNewsObserver();

        newsPresenter.bind();
        verify(view).showLoading(true);
        testScheduler.triggerActions();
        verify(view).showLoading(false);
        verify(view).setError();
    }

}
