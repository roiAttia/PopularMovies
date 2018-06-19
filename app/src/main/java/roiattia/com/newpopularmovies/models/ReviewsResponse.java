package roiattia.com.newpopularmovies.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class ReviewsResponse {

    @SerializedName("page")
    public abstract int page();
    @SerializedName("results")
    public abstract List<UserReview> results();
    @SerializedName("total_results")
    public abstract int totalResults();
    @SerializedName("total_pages")
    public abstract int totalPages();

    public static TypeAdapter<ReviewsResponse> typeAdapter(Gson gson) {
        return new AutoValue_ReviewsResponse.GsonTypeAdapter(gson);
    }
}