package roiattia.com.newpopularmovies.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class UserReview {

    @SerializedName("author")
    public abstract String author();
    @SerializedName("content")
    public abstract String review();

    public static TypeAdapter<UserReview> typeAdapter(Gson gson) {
        return new AutoValue_UserReview.GsonTypeAdapter(gson);
    }
}