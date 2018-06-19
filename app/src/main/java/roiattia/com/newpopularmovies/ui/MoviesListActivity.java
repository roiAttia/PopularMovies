package roiattia.com.newpopularmovies.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;
import roiattia.com.newpopularmovies.utils.FetchDataUtil;

public class MoviesListActivity extends AppCompatActivity
    implements MoviesAdapter.MovieAdapterOnClickHandler, FetchDataUtil.FetchDataUtilHandler{

    private List<Movie> mMovies;
    private MoviesListFragment mMoviesListFragment;
    private MovieDetailsFragment mMovieDetailsFragment;
    private FragmentManager mFragmentManager;
    private FetchDataUtil mFetchDataUtil;
    private String mCategory;
    private boolean mIsTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        mIsTwoPane = getResources().getBoolean(R.bool.is_tablet);

        // check for saved instance state data
        if(savedInstanceState != null){
            mCategory = savedInstanceState.getString(ConstantsUtil.CATEGORY);
            mMoviesListFragment = (MoviesListFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, ConstantsUtil.MOVIES_LIST_FRAGMENT);
            // if on tablet then add mMovieDetailsFragment
            if(mIsTwoPane){
                mMovieDetailsFragment = (MovieDetailsFragment) getSupportFragmentManager()
                        .getFragment(savedInstanceState, ConstantsUtil.MOVIE_DETAILS_FRAGMENT);
            }
        } else {
            mCategory = getString(R.string.menu_item_popularity);
            mMoviesListFragment = new MoviesListFragment();
            mMovieDetailsFragment = new MovieDetailsFragment();
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fl_movies, mMoviesListFragment)
                    .commit();
        }

        mFetchDataUtil = new FetchDataUtil(this, this);
        mFetchDataUtil.fetchMoviesList(mCategory);
    }

    @Override
    public void onMovieClick(int movieIndex) {
        if(mIsTwoPane) {
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
    public void onPreExecute(String categoryName) {
        mCategory = categoryName;
        getSupportActionBar().setTitle(categoryName);
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute(List movieList) {
        mMovies = movieList;
        mMoviesListFragment.setMoviesData(mMovies);
    }

    @Override
    public void onFailureExecute() {
        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        switch (menuItemId){
            case R.id.mi_popularity:
                mFetchDataUtil.fetchMoviesList(getString(R.string.menu_item_popularity));
                break;
            case R.id.mi_top_rated:
                mFetchDataUtil.fetchMoviesList(getString(R.string.menu_item_top_rated));
                break;
            case R.id.mi_favorites:
//                loadData(getString(R.string.menu_item_favorites));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ConstantsUtil.CATEGORY, mCategory);
        getSupportFragmentManager()
                .putFragment(outState, ConstantsUtil.MOVIES_LIST_FRAGMENT, mMoviesListFragment);
        if(mIsTwoPane){
            getSupportFragmentManager()
                    .putFragment(outState, ConstantsUtil.MOVIE_DETAILS_FRAGMENT, mMovieDetailsFragment);
        }
    }
}
