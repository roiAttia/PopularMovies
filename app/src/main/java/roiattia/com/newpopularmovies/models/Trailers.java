package roiattia.com.newpopularmovies.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Trailers {

    @SerializedName("key")
    public abstract String key();
    @SerializedName("type")
    public abstract String type();

    public static TypeAdapter<Trailers> typeAdapter(Gson gson) {
        return new AutoValue_Trailers.GsonTypeAdapter(gson);
    }
}