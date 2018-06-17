package roiattia.com.newpopularmovies.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class MovieResponse {

    @SerializedName("page")
    public abstract int page();
    @SerializedName("results")
    public abstract List<Movie> results();
    @SerializedName("total_results")
    public abstract int totalResults();
    @SerializedName("total_pages")
    public abstract int totalPages();

    public static TypeAdapter<MovieResponse> typeAdapter(Gson gson) {
        return new AutoValue_MovieResponse.GsonTypeAdapter(gson);
    }
}
