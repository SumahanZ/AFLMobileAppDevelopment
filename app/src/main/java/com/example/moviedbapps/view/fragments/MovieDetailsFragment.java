package com.example.moviedbapps.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedbapps.R;
import com.example.moviedbapps.adapter.MovieProductionCompaniesAdapter;
import com.example.moviedbapps.helper.Const;
import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.viewmodel.MovieViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView movie_title, movie_tagline, movie_overview, movie_avg,
            movie_vote, movie_release_date, movie_popularity, movie_genres, movie_runtime;
    private MovieViewModel viewmodel;
    private ImageView movie_backdrop, movie_poster;
    private RecyclerView movie_details_rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        movie_title = view.findViewById(R.id.lbl_movie_details_fragment_title);
        //Sets recyclerview to horizontal
        movie_details_rv = view.findViewById(R.id.movie_details_fragment_recyclerview);
        movie_details_rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //
        movie_tagline = view.findViewById(R.id.lbl_movie_details_fragment_tagline);
        movie_overview = view.findViewById(R.id.lbl_movie_details_fragment_overview);
        movie_avg = view.findViewById(R.id.lbl_movie_details_fragment_avg);
        movie_vote = view.findViewById(R.id.lbl_movie_details_fragment_vote_count);
        movie_release_date = view.findViewById(R.id.lbl_movie_details_fragment_release_date);
        movie_popularity = view.findViewById(R.id.lbl_movie_details_fragment_popularity);
        movie_backdrop = view.findViewById(R.id.img_movie_details_backdrop);
        movie_genres = view.findViewById(R.id.lbl_movie_details_fragment_genres);
        movie_runtime = view.findViewById(R.id.lbl_movie_details_fragment_runtime);
        movie_poster = view.findViewById(R.id.imageView2);

        viewmodel = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);

        String id = getArguments().getString("movie_id");
        viewmodel.getMovieById(id);
        viewmodel.getResultGetMovieById().observe(getViewLifecycleOwner(), showResultMovie);
        return view;
    }



    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String movie_titles = "" + movies.getTitle();
            String movie_taglines = "'" + movies.getTagline() + "'";
            String movie_overviews = "" + movies.getOverview();
            String movie_averages = "Vote: " + movies.getVote_average();
            String movie_votes = "(" + movies.getVote_count() + ")";
            String movie_release_dates = "" + movies.getRelease_date();
            String movie_popularitys = "Popularity: " + movies.getPopularity();
            String movie_backdrop_path = "" + movies.getBackdrop_path();
            String movie_runtimes = "" + movies.getRuntime() + " minutes";
            String movie_poster_path = "" + movies.getPoster_path();
            List<Movies.Genres> movieGenres = movies.getGenres();
            List<Movies.ProductionCompanies> movieProductionCompanies = movies.getProduction_companies();

            Glide.with(MovieDetailsFragment.this).load(Const.IMG_URL + movie_backdrop_path).into(movie_backdrop);

            Glide.with(MovieDetailsFragment.this).load(Const.IMG_URL + movie_poster_path).into(movie_poster);
            movie_title.setText(movie_titles);
            if(movie_taglines.equals("''")){
                movie_tagline.setText("");
            }else{
                movie_tagline.setText(movie_taglines);
            }
            movie_overview.setText(movie_overviews);
            movie_avg.setText(movie_averages);
            movie_vote.setText(movie_votes);
            movie_release_date.setText(movie_release_dates);
            movie_popularity.setText(movie_popularitys);
            movie_runtime.setText(movie_runtimes);

            if (movieGenres.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < movieGenres.size(); i++) {
                    if (i == movieGenres.size() - 1) {
                        stringBuilder.append(movieGenres.get(i).getName());
                    } else {
                        stringBuilder.append(movieGenres.get(i).getName()).append(", ");
                    }
                }
                movie_genres.setText(stringBuilder.toString());
            }

            //Make a new instance of the adapter and putting data from CardView in adapter into RecyclerView
            if(movieProductionCompanies.size() > 0){
                MovieProductionCompaniesAdapter movieProductionCompaniesAdapter = new MovieProductionCompaniesAdapter(getActivity());
                movieProductionCompaniesAdapter.setListPC(movies.getProduction_companies());
                movie_details_rv.setAdapter(movieProductionCompaniesAdapter);

            }
        }
    };
}