package roiattia.com.newpopularmovies.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roiattia.com.newpopularmovies.BuildConfig;
import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Movie;
import roiattia.com.newpopularmovies.models.MovieResponse;
import roiattia.com.newpopularmovies.rest.RetrofitClient;
import roiattia.com.newpopularmovies.rest.TheMoviesDbService;
import roiattia.com.newpopularmovies.utils.FetchDataUtil;

public class MoviesListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_movies_list);
        return rootView;
    }

    public void setMoviesData(List<Movie> movieList) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(getContext(), (MoviesListActivity) getActivity());
        moviesAdapter.setMoviesDate(movieList);
        mRecyclerView.setAdapter(moviesAdapter);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }
}
