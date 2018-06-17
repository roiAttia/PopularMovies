package roiattia.com.newpopularmovies.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.utils.FetchDataUtil;

public class MoviesListActivity extends AppCompatActivity
    implements MoviesAdapter.MovieAdapterOnClickHandler, FetchDataUtil.FetchDataUtilHandler{

    private List<Movie> mMovies;
    private MoviesListFragment mMoviesListFragment;
    private MovieDetailsFragment mMovieDetailsFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        FetchDataUtil fetchDataUtil = new FetchDataUtil(this);
        fetchDataUtil.fetchMoviesList(FetchDataUtil.POPULARITY);

        mMoviesListFragment = new MoviesListFragment();
        mMovieDetailsFragment = new MovieDetailsFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fl_movies, mMoviesListFragment)
                .commit();
    }

    @Override
    public void onMovieClick(int movieIndex) {
        boolean isTwoPane = getResources().getBoolean(R.bool.is_tablet);
        if(isTwoPane) {
            mMovieDetailsFragment.setMovieData(mMovies.get(movieIndex));
            mFragmentManager.beginTransaction()
                    .replace(R.id.fl_movies, mMovieDetailsFragment)
                    .commit();
        } else {
            Intent intent = new Intent(MoviesListActivity.this, MovieDetailsActivity.class);
            intent.putExtra("name", mMovies.get(movieIndex));
            startActivity(intent);
        }
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
