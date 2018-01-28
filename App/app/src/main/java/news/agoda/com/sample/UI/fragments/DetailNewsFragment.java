package news.agoda.com.sample.UI.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;
import news.agoda.com.sample.mvp.model.NewsEntity;

/**
 * Created by juan.ocampo on 28/01/2018.
 */

public class DetailNewsFragment extends Fragment {
    private String storyURL = "";

    private TextView titleView;
    private DraweeView imageView;
    private TextView summaryView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView = view.findViewById(R.id.title);
        imageView = view.findViewById(R.id.news_image);
        summaryView = view.findViewById(R.id.summary_content);

    }

    public void setUI(NewsEntity newsEntity) {
        titleView.setText(newsEntity.getTitle());
        summaryView.setText(newsEntity.getAbstract());

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(Uri.parse(newsEntity.getImageUrl())))
                .setOldController(imageView.getController()).build();
        imageView.setController(draweeController);
    }

    private void onFullStoryClicked() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(storyURL));
        startActivity(intent);
    }
}
