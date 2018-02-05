package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import news.agoda.com.sample.mvp.model.FrescoResponse;
import news.agoda.com.sample.network.FrescoApi;
import news.agoda.com.sample.network.FrescoRetrofitApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by juan.ocampo on 4/02/2018.
 */

public class FrescoRetrofitApiTest {

    @Mock
    FrescoApi frescoApi;

    @Mock
    Call<FrescoResponse> frescoCall;

    Response<FrescoResponse> frescoRetrofitApiResponse;

    @Mock
    FrescoResponse frescBoby;

    @Mock
    Subscriber<FrescoResponse> subscriber;

    @Mock
    ResponseBody responseBody;

    private FrescoRetrofitApi frescoRetrofitApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        frescoRetrofitApi = new FrescoRetrofitApi(frescoApi);
    }

    @Test
    public void getNewsFromServiceTest() throws IOException {
        frescoRetrofitApiResponse = Response.success(frescBoby);

        doReturn(frescoCall).when(frescoApi).getNews();
        doReturn(frescoRetrofitApiResponse).when(frescoCall).execute();

        frescoRetrofitApi.getNewsFromService(subscriber);
        verify(subscriber).onNext(frescBoby);
        verify(subscriber).onCompleted();
    }

    @Test
    public void getNewsFromServiceErrorTest() throws IOException {
        frescoRetrofitApiResponse = Response.error(401, responseBody);

        doReturn(frescoCall).when(frescoApi).getNews();
        doReturn(frescoRetrofitApiResponse).when(frescoCall).execute();

        frescoRetrofitApi.getNewsFromService(subscriber);
        verify(subscriber).onError(Matchers.<Throwable>any());
    }
}
