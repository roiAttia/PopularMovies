package roiattia.com.newpopularmovies.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;

public class MovieDetailsFragment extends Fragment {

    private Movie mMovie;
    @BindView(R.id.tv_overview) TextView overviewText;
    @BindView(R.id.tv_year_released) TextView releaseDateText;
    @BindView(R.id.tv_avg_rating) TextView averageRatingText;
    @BindView(R.id.iv_poster) ImageView posterImage;

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
}
