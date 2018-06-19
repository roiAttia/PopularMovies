package roiattia.com.newpopularmovies.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class TrailersResponse {

    @SerializedName("results")
    public abstract List<Trailers> results();

    public static TypeAdapter<TrailersResponse> typeAdapter(Gson gson) {
        return new AutoValue_TrailersResponse.GsonTypeAdapter(gson);
    }
}
