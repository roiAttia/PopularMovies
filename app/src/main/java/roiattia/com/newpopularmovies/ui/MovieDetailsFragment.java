package roiattia.com.newpopularmovies.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.Trailers;
import roiattia.com.newpopularmovies.models.UserReview;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;

public class MovieDetailsFragment extends Fragment {

    private Movie mMovie;
    private List<UserReview> mUserReviews;
    private List<Trailers> mTrailers;
    @BindView(R.id.tv_overview) TextView overviewText;
    @BindView(R.id.tv_year_released) TextView releaseDateText;
    @BindView(R.id.tv_avg_rating) TextView averageRatingText;
    @BindView(R.id.tv_reviews) TextView reviewsText;
    @BindView(R.id.tv_reviews_headline) TextView reviewsHeadlineText;
    @BindView(R.id.tv_trailers_headline) TextView trailersHeadlineText;
    @BindView(R.id.iv_poster) ImageView posterImage;
    @BindView(R.id.rv_trailers) RecyclerView trailersList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, rootView);

        overviewText.setText(mMovie.overview());
        releaseDateText.setText(mMovie.releaseDate());
        averageRatingText.setText(String.valueOf(mMovie.voteAverage()));
        Picasso.with(getContext())
                .load(ConstantsUtil.MOVIES_BASE_URL_POSTER_PATH +
                        ConstantsUtil.MOVIES_POSTER_SIZE +
                        mMovie.posterPath())
                .fit()
                .into(posterImage);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setMovieData(Movie movie) {
        mMovie = movie;
    }

    public void setReviewsData(List<UserReview> userReviewList) {
        mUserReviews = userReviewList;
        updateReviews();
    }

    public void setTrailersData(List<Trailers> trailers){
        mTrailers = trailers;
        updateTrailers();
    }

    private void updateTrailers() {
        if(mTrailers.size() == 0){
            trailersHeadlineText.setText(R.string.no_trailers_message);
        } else {
            trailersHeadlineText.setText(R.string.watch_trailers_message);
            TrailersAdapter trailersAdapter = new TrailersAdapter(getContext(),
                    (TrailersAdapter.TrailersAdapterOnClickHandler) getActivity());
            trailersAdapter.setTrailersData(mTrailers);
            trailersList.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            trailersList.setLayoutManager(layoutManager);
            trailersList.setAdapter(trailersAdapter);
        }
    }

    private void updateReviews() {
        if(mUserReviews.size() == 0){
            reviewsHeadlineText.setText(R.string.no_reviews_message);
        } else {
            reviewsHeadlineText.setText(R.string.users_reviews);
            for (UserReview userReview : mUserReviews) {
                reviewsText.append(Html.fromHtml("<b>" + getString(R.string.review_by)
                        + " " + userReview.author() + "</b>" + "<br>" + userReview.review() + "<br><br>"));
            }
        }
    }
}
