package roiattia.com.newpopularmovies.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.Trailers;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;
import roiattia.com.newpopularmovies.utils.FetchMovieDetailsUtil;

import static roiattia.com.newpopularmovies.utils.ConstantsUtil.REVIEWS;
import static roiattia.com.newpopularmovies.utils.ConstantsUtil.TRAILERS;

public class MovieDetailsActivity extends AppCompatActivity
    implements FetchMovieDetailsUtil.FetchDataUtilHandler,
        TrailersAdapter.TrailersAdapterOnClickHandler{

    private static final String TAG = MoviesListActivity.class.getSimpleName();
    private MovieDetailsFragment mMovieDetailsFragment;
    private List<Trailers> mTrailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get intent from calling activity
        Bundle data = getIntent().getExtras();
        Movie movie = data.getParcelable("name");

        FetchMovieDetailsUtil fetchMovieDetailsUtil = new FetchMovieDetailsUtil(this, this);
        fetchMovieDetailsUtil.fetchReviews(movie.id());
        fetchMovieDetailsUtil.fetchTrailers(movie.id());

        getSupportActionBar().setTitle(movie.title());
        ImageView imageView = findViewById(R.id.iv_backdrop);
        String moviePosterPath = movie.backdropPath();
        Picasso.with(this)
                .load(ConstantsUtil.MOVIES_BASE_URL_POSTER_PATH +
                        ConstantsUtil.MOVIES_POSTER_SIZE +
                        moviePosterPath)
                .fit()
                .into(imageView);

        mMovieDetailsFragment = new MovieDetailsFragment();
        mMovieDetailsFragment.setMovieData(movie);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl_details_place_holder, mMovieDetailsFragment)
                .commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(List movieDataList, String type) {
        if(type.equals(REVIEWS)) {
            mMovieDetailsFragment.setReviewsData(movieDataList);
        } else if(type.equals(TRAILERS)){
            mMovieDetailsFragment.setTrailersData(movieDataList);
            mTrailers = movieDataList;
        }
    }

    @Override
    public void onFailureExecute() { }

    @Override
    public void onClick(int trailerIndex) {
        String trailerPath = mTrailers.get(trailerIndex).key();
        String youTubePath = ConstantsUtil.YOUTUBE_BASE_URL_PATH + trailerPath;
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerPath));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(youTubePath));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}
