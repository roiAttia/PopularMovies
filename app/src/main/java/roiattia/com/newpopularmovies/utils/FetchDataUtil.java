package roiattia.com.newpopularmovies.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roiattia.com.newpopularmovies.BuildConfig;
import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.MovieResponse;
import roiattia.com.newpopularmovies.models.UserReview;
import roiattia.com.newpopularmovies.rest.RetrofitClient;
import roiattia.com.newpopularmovies.rest.TheMoviesDbService;

public class FetchDataUtil {

    private TheMoviesDbService mService;
    private Context mContext;
    private final FetchDataUtilHandler mHandler;

    public FetchDataUtil(Context context, FetchDataUtilHandler handler){
        mService = RetrofitClient.getClient().create(TheMoviesDbService.class);
        mHandler = handler;
        mContext = context;
    }

    public interface FetchDataUtilHandler{
        void onPreExecute(String categoryName);
        void onPostExecute(List<Movie> movieList);
        void onFailureExecute();
    }

    public void fetchMoviesList(String category) {
        mHandler.onPreExecute(category);
        Call<MovieResponse> call = null;
        if(category.equals(mContext.getString(R.string.menu_item_popularity))){
            call = mService.getPopularMovies(BuildConfig.API_KEY);
        } else if(category.equals(mContext.getString(R.string.menu_item_top_rated))){
            call = mService.getTopRatedMovies(BuildConfig.API_KEY);
        }
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().results();
                mHandler.onPostExecute(movies);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mHandler.onFailureExecute();
            }
        });
    }
}
