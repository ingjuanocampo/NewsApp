package news.agoda.com.sample.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.UI.adapters.NewsAdapter;
import news.agoda.com.sample.UI.fragments.DetailNewsFragment;
import news.agoda.com.sample.mvp.model.NewsEntity;
import news.agoda.com.sample.mvp.NewsPresenter;
import news.agoda.com.sample.mvp.NewsView;

/**
 * Created by juanocampo on 1/26/18.
 */

public class NewsActivity extends AppCompatActivity implements NewsAdapter.NewsCardListener {

    private boolean isTablet;
    private DetailNewsFragment detailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Fresco.initialize(this);
        isTablet = getResources().getBoolean(R.bool.isTable);
        if (isTablet) {
            detailFragment = (DetailNewsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        }
    }

    @Override
    public void onNewsClicked(NewsEntity entity) {
        if (isTablet) {
            detailFragment.setUI(entity);
        } else {
            startActivity(DetailViewActivity.newInstance(this, entity));
        }
    }
}
