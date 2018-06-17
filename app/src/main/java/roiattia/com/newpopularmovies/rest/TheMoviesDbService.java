package roiattia.com.newpopularmovies.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.MovieResponse;


public interface TheMoviesDbService {

    @GET("movie/popular?sort=desc")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated?sort=desc")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
