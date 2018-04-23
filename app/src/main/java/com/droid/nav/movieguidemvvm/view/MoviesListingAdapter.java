package com.droid.nav.movieguidemvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.droid.nav.movieguidemvvm.R;
import com.droid.nav.movieguidemvvm.databinding.MovieGridItemBinding;
import com.droid.nav.movieguidemvvm.model.Movie;
import com.droid.nav.movieguidemvvm.network.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author navdeep
 */
public class MoviesListingAdapter extends RecyclerView.Adapter<MoviesListingAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    public void addAll(List<Movie> movies) {



        if (this.movies == null || this.movies.isEmpty()) {
            this.movies = movies;
            notifyItemRangeInserted(0, movies.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MoviesListingAdapter.this.movies.size();
                }

                @Override
                public int getNewListSize() {
                    return movies.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MoviesListingAdapter.this.movies.get(oldItemPosition).getId() ==
                            movies.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Movie project = movies.get(newItemPosition);
                    Movie old = movies.get(oldItemPosition);
                    return project.getId() == old.getId()
                            && Objects.equals(project.getTitle(), old.getTitle());
                }
            });
//            this.movies = movies;
            result.dispatchUpdatesTo(this);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public Movie movie;
        public MovieGridItemBinding itemBinding;

        public ViewHolder(MovieGridItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;

        }

        @Override
        public void onClick(View view) {

        }
    }

    public MoviesListingAdapter() {
        this.movies = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        MovieGridItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_grid_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.itemBinding.movieName.setText(holder.movie.getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(Api.getPosterPath(holder.movie.getPosterPath()))
                .apply(options)
                .into(new BitmapImageViewTarget(holder.itemBinding.moviePoster) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                    }
                });
    }

    private void setBackgroundColor(Palette palette, ViewHolder holder) {
        holder.itemBinding.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.black_translucent_60)));
    }


    @Override
    public int getItemCount() {

        return movies.size();
    }
}
