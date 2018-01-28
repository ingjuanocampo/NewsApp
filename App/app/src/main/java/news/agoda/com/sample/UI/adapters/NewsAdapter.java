package news.agoda.com.sample.UI.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.mvp.model.MediaEntity;
import news.agoda.com.sample.mvp.model.NewsEntity;
import news.agoda.com.sample.network.FrescoRetrofitApi;

/**
 * Created by juanocampo on 1/27/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public interface NewsCardListener {
        void onNewsClicked(NewsEntity entity);
    }

    private List<NewsEntity> newsEntities = new ArrayList<>();

    public NewsAdapter(NewsCardListener listener) {
        this.listener = listener;
    }

    public final NewsCardListener listener;

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        holder.bind(newsEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return newsEntities.size();
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
        notifyItemRangeInserted(0, newsEntities.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView newsTitle;
        private final DraweeView imageView;
        private NewsEntity entity;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false));
            newsTitle = itemView.findViewById(R.id.news_title);
            imageView = itemView.findViewById(R.id.news_item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNewsClicked(entity);
                }
            });
        }

        public void bind(NewsEntity entity) {
            this.entity = entity;
            newsTitle.setText(entity.getTitle());
            String url = entity.getImageUrl();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri
                    (Uri.parse(url))).setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
    }

}
