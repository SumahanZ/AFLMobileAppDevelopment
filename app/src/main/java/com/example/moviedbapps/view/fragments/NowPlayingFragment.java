package com.example.moviedbapps.view.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviedbapps.R;
import com.example.moviedbapps.adapter.NowPlayingAdapter;
import com.example.moviedbapps.helper.PaginationScrollListener;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.retrofit.ApiService;
import com.example.moviedbapps.view.activities.NowPlayingActivity;
import com.example.moviedbapps.viewmodel.MovieViewModel;
import com.example.moviedbapps.helper.ItemClickSupport;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private RecyclerView rv_now_playing;
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
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        rv_now_playing = view.findViewById(R.id.rv_now_playing_fragment);
        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getNowPlayingData();
        view_model.getResultGetNowPlaying().observe(getActivity(),showNowPlaying);
        return view;
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rv_now_playing.setLayoutManager(mLayoutManager);
            rv_now_playing.setItemAnimator(new DefaultItemAnimator());
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);



            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                    return false;
                }
            });
            //Item Click through ItemClickSupport di Fragment
            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
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
                            bundle.putString("movie_id", ""+ nowPlaying.getResults().get(position).getId());
                            Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_movie_details_Fragment2, bundle);
                        }
                    },1500);
                }
            });
        }
    };
}