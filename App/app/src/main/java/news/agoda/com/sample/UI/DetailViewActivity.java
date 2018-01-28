package news.agoda.com.sample.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import news.agoda.com.sample.R;
import news.agoda.com.sample.UI.fragments.DetailNewsFragment;
import news.agoda.com.sample.mvp.model.NewsEntity;

/**
 * News detail view
 */
public class DetailViewActivity extends AppCompatActivity {

    private static final String NEWS_ENTRY = "News_Entry";

    public static Intent newInstance(Context context, NewsEntity newsEntity) {
        
        Bundle args = new Bundle();
        args.putSerializable(NEWS_ENTRY, newsEntity);
        Intent intent= new Intent(context, DetailViewActivity.class);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailNewsFragment fragment = (DetailNewsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        NewsEntity newsEntity = (NewsEntity) getIntent().getExtras().getSerializable(NEWS_ENTRY); 
        fragment.setUI(newsEntity);
    }

}
