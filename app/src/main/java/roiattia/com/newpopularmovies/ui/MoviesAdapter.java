package roiattia.com.newpopularmovies.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieAdapterViewHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private List<Movie> mMovieList;
    private final Context mContext;
    private final MovieAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onMovieClick messages.
     */
    public interface MovieAdapterOnClickHandler {
        void onMovieClick(int movieIndex);
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
                .load(ConstantsUtil.MOVIES_BASE_URL_POSTER_PATH +
                        ConstantsUtil.MOVIES_POSTER_SIZE +
                        moviePosterPath)
                .fit()
                .into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        final ImageView mMoviePoster;

        MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onMovieClick(adapterPosition);
        }
    }

    public void setMoviesDate(List<Movie> movieList){
        mMovieList = movieList;
    }
}
