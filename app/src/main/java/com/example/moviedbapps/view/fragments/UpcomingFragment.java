package com.example.moviedbapps.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedbapps.R;
import com.example.moviedbapps.adapter.NowPlayingAdapter;
import com.example.moviedbapps.adapter.UpcomingAdapter;
import com.example.moviedbapps.helper.ItemClickSupport;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.model.Upcoming;
import com.example.moviedbapps.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rv_upcoming;
    private MovieViewModel view_model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        rv_upcoming= view.findViewById(R.id.rv_upcoming_fragment);
        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getUpcomingData();
        view_model.getResultGetUpComing().observe(getActivity(),showUpcoming);
        return view;
    }

    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            rv_upcoming.setLayoutManager(new LinearLayoutManager(getActivity()));

            UpcomingAdapter adapter = new UpcomingAdapter(getActivity());
            adapter.setListUpcoming(upcoming.getResults());
            rv_upcoming.setAdapter(adapter);

            ItemClickSupport.addTo(rv_upcoming).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                    return false;
                }
            });
            //Item Click through ItemClickSupport di Fragment
            ItemClickSupport.addTo(rv_upcoming).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.item_progress);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Bundle bundle = new Bundle();
                            bundle.putString("movie_id", ""+ upcoming.getResults().get(position).getId());
                            Navigation.findNavController(v).navigate(R.id.action_upcomingFragment_to_movie_details_Fragment, bundle);
                        }
                    },1500);


                }
            });
        }
    };


}