package news.agoda.com.sample.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.UI.adapters.NewsAdapter;
import news.agoda.com.sample.mvp.model.NewsEntity;
import news.agoda.com.sample.mvp.NewsPresenter;
import news.agoda.com.sample.mvp.NewsView;

/**
 * Created by juanocampo on 1/26/18.
 */

public class NewsActivity extends AppCompatActivity implements NewsView {


    private NewsPresenter presenter;
    private NewsAdapter adapter;
    private View loader;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Fresco.initialize(this);
        recyclerView = findViewById(R.id.news_recyclerview);
        loader = findViewById(R.id.loader_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
        presenter = new NewsPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind();
    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.unBind();
    }

    @Override
    public void setNews(List<NewsEntity> newsEntity) {
        loader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setNewsEntities(newsEntity);
    }

    @Override
    public void setError() {
        // TODO create a default error screen
    }



}
