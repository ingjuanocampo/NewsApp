package news.agoda.com.sample.mvp;

import java.util.List;

import news.agoda.com.sample.model.NewsEntity;

/**
 * Created by juanocampo on 1/26/18.
 */

public interface NewsView {
    void setNews(List<NewsEntity> newsEntity);
    void setError();
}
