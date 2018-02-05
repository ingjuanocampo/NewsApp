package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import news.agoda.com.sample.mvp.DataModel;
import news.agoda.com.sample.mvp.model.FrescoResponse;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

public class ModelViewTest {

    @Mock
    DataModel dataModel;

    @Mock
    FrescoResponse frescoResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFrescoResponse() throws Exception {
        doReturn(Observable.just(frescoResponse)).when(dataModel).getFrescoNewsObserver();

        TestSubscriber<FrescoResponse> subscriber = TestSubscriber.create();
        dataModel.getFrescoNewsObserver().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
        assertTrue(subscriber.getOnNextEvents().get(0) instanceof FrescoResponse);
        assertEquals(subscriber.getOnNextEvents().get(0), frescoResponse);
    }
}
