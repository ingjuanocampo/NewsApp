package news.agoda.com.sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juanocampo on 1/27/18.
 */

public class FrescoResponse {


    @SerializedName("results")
    private List<NewsEntity> newsEntities;

    public List<NewsEntity> getNewsEntities() {
        return newsEntities;
    }
}
