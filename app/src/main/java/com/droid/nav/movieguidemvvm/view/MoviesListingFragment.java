package com.droid.nav.movieguidemvvm.view;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.droid.nav.movieguidemvvm.R;
import com.droid.nav.movieguidemvvm.databinding.FragmentMoviesBinding;
import com.droid.nav.movieguidemvvm.di.Injectable;
import com.droid.nav.movieguidemvvm.view_model.MovieViewModel;


import javax.inject.Inject;

public class MoviesListingFragment extends Fragment implements Injectable {

    private FragmentMoviesBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MoviesListingAdapter moviesListingAdapter;
    private int page=1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        moviesListingAdapter = new MoviesListingAdapter();
        binding.moviesListing.setLayoutManager(layoutManager);
        binding.moviesListing.setAdapter(moviesListingAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final MovieViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MovieViewModel.class);

        observeViewModel(viewModel);

        binding.moviesListing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    page++;
                    viewModel.fetchMovieList(page);
                }
            }
        });
    }

    private void observeViewModel(MovieViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getMovieWrapper().observe(this, moviesWraper -> {
            if (moviesWraper != null) {

                binding.setIsLoading(false);
                moviesListingAdapter.addAll(moviesWraper.getMovieList());
            }
        });

    }



}