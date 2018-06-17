package roiattia.com.newpopularmovies.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roiattia.com.newpopularmovies.BuildConfig;
import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.rest.RetrofitClient;
import roiattia.com.newpopularmovies.rest.TheMoviesDbService;
import roiattia.com.newpopularmovies.utils.FetchDataUtil;

public class MoviesListActivity extends AppCompatActivity
    implements MoviesAdapter.MovieAdapterOnClickHandler, FetchDataUtil.FetchDataUtilHandler{

    private List<Movie> mMovies;
    private MoviesListFragment mMoviesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        FetchDataUtil fetchDataUtil = new FetchDataUtil(this);
        fetchDataUtil.fetchMoviesList(FetchDataUtil.POPULARITY);

        mMoviesListFragment = new MoviesListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl_movies, mMoviesListFragment)
                .commit();
    }

    @Override
    public void onClick(int movieIndex) {
        Toast.makeText(this, mMovies.get(movieIndex).title(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreExecute() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute(List<Movie> movieList) {
        mMovies = movieList;
        mMoviesListFragment.setMoviesData(mMovies);
    }

    @Override
    public void onFailureExecute() {
        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
    }
}
