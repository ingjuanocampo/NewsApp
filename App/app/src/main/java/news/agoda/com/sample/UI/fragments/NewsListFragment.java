package news.agoda.com.sample.UI.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.UI.adapters.NewsAdapter;
import news.agoda.com.sample.mvp.DataModel;
import news.agoda.com.sample.mvp.NewsPresenter;
import news.agoda.com.sample.mvp.NewsView;
import news.agoda.com.sample.mvp.model.NewsEntity;

/**
 * Created by juan.ocampo on 28/01/2018.
 */

public class NewsListFragment extends Fragment implements NewsView  {

    private NewsPresenter presenter;
    private NewsAdapter adapter;
    private View loader;
    private RecyclerView recyclerView;
    private boolean isTablet;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof NewsAdapter.NewsCardListener) {

        } else {
            throw new ClassCastException("Parent activity should implement NewsCardListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.news_recyclerview);
        loader = view.findViewById(R.id.loader_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter((NewsAdapter.NewsCardListener) getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new NewsPresenter(this, new DataModel());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unBind();
    }

    @Override
    public void setNews(List<NewsEntity> newsEntities) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setNewsEntities(newsEntities);
        isTablet = getResources().getBoolean(R.bool.isTable);
        if (isTablet) {
            ((NewsAdapter.NewsCardListener) getActivity()).onNewsClicked(newsEntities.get(0)); // By default 0 is selected
        }
    }

    @Override
    public void showLoading(boolean show) {
        loader.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setError() {
        // TODO create a default error screen
    }
}
