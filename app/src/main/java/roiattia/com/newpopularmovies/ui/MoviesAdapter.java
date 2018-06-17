package roiattia.com.newpopularmovies.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieAdapterViewHolder> {

    /* the url for movie's poster with size of w185 */
    public static final String MOVIES_BASE_URL_POSTER_PATH =
            "http://image.tmdb.org/t/p/";

    /* the url for movie's poster with size of w185 */
    public static final String YOUTUBE_BASE_URL_PATH =
            "https://www.youtube.com/watch?v=";

    /* the url for movie's poster with size of w185 */
    public static final String MOVIES_POSTER_SIZE =
            "w342/";

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private List<Movie> mMovieList;
    private Context mContext;
    private final MovieAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface MovieAdapterOnClickHandler {
        void onClick(int movieIndex);
    }

    public MoviesAdapter(Context context, MovieAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.list_item_movie;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String moviePosterPath = mMovieList.get(position).posterPath();
        Picasso.with(mContext)
                .load(MOVIES_BASE_URL_POSTER_PATH + MOVIES_POSTER_SIZE + moviePosterPath)
                .fit()
                .into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView mMoviePoster;

        MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

    public void setMoviesDate(List<Movie> movieList){
        mMovieList = movieList;
    }
}
