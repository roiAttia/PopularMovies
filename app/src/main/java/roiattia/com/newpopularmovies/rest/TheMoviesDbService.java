package roiattia.com.newpopularmovies.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.MovieResponse;
import roiattia.com.newpopularmovies.models.ReviewsResponse;
import roiattia.com.newpopularmovies.models.TrailersResponse;


public interface TheMoviesDbService {

    @GET("movie/popular?sort=desc")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated?sort=desc")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewsResponse> getUserReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailersResponse> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);
}
