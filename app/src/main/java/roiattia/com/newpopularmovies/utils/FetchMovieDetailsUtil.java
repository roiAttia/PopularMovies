package roiattia.com.newpopularmovies.utils;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roiattia.com.newpopularmovies.BuildConfig;
import roiattia.com.newpopularmovies.models.ReviewsResponse;
import roiattia.com.newpopularmovies.models.Trailers;
import roiattia.com.newpopularmovies.models.TrailersResponse;
import roiattia.com.newpopularmovies.models.UserReview;
import roiattia.com.newpopularmovies.rest.RetrofitClient;
import roiattia.com.newpopularmovies.rest.TheMoviesDbService;

public class FetchMovieDetailsUtil {

    private TheMoviesDbService mService;
    private Context mContext;
    private final FetchDataUtilHandler mHandler;

    public FetchMovieDetailsUtil(Context context,FetchDataUtilHandler handler){
        mService = RetrofitClient.getClient().create(TheMoviesDbService.class);
        mHandler = handler;
        mContext = context;
    }

    public interface FetchDataUtilHandler{
        void onPreExecute();
        void onPostExecute(List movieDataList, String type);
        void onFailureExecute();
    }

    public void fetchReviews(int movieId) {
        Call<ReviewsResponse> call = mService.getUserReviews(movieId , BuildConfig.API_KEY);
        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                List<UserReview> userReviewList = response.body().results();
                mHandler.onPostExecute(userReviewList, ConstantsUtil.REVIEWS);
            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                mHandler.onFailureExecute();
            }
        });
    }

    public void fetchTrailers(int movieId) {
        Call<TrailersResponse> call = mService.getTrailers(movieId , BuildConfig.API_KEY);
        call.enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                List<Trailers> trailers = response.body().results();
                mHandler.onPostExecute(trailers, ConstantsUtil.TRAILERS);
            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {
                mHandler.onFailureExecute();
            }
        });
    }
}
