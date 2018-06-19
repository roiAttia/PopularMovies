package roiattia.com.newpopularmovies.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Movie implements Parcelable {

    @SerializedName("id")
    public abstract int id();
    @SerializedName("original_title")
    public abstract String originalTitle();
    @SerializedName("title")
    public abstract String title();
    @SerializedName("vote_average")
    public abstract double voteAverage();
    @SerializedName("popularity")
    public abstract double popularity();
    @SerializedName("poster_path")
    public abstract String posterPath();
    @SerializedName("backdrop_path")
    public abstract String backdropPath();
    @SerializedName("overview")
    public abstract String overview();
    @SerializedName("release_date")
    public abstract String releaseDate();

    public static TypeAdapter<Movie> typeAdapter(Gson gson) {
        return new AutoValue_Movie.GsonTypeAdapter(gson);
    }
}
