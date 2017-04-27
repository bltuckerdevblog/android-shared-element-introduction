package com.bltucker.transitiontutorial.teamlist;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.glide.GlideRequestProvider;
import com.bltucker.transitiontutorial.data.TeamsItem;
import com.bltucker.transitiontutorial.databinding.AdapterTeamListItemBinding;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamListItemViewHolder> {

    private TeamListModel teamListModel;
    private final TeamListViewPresenter presenter;
    private final GlideRequestProvider glideRequestProvider;

    @Inject
    TeamListAdapter(TeamListModel teamListModel,
                    TeamListViewPresenter presenter,
                    GlideRequestProvider glideRequestProvider) {
        this.teamListModel = teamListModel;
        this.presenter = presenter;
        this.glideRequestProvider = glideRequestProvider;
    }

    void updateAdapter(TeamListModel model) {
        this.teamListModel = model;
        notifyDataSetChanged();
    }

    @Override
    public TeamListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterTeamListItemBinding binding = AdapterTeamListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        final TeamListItemViewHolder teamListItemViewHolder = new TeamListItemViewHolder(binding, glideRequestProvider);

        binding.teamItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = teamListItemViewHolder.getAdapterPosition();
                presenter.onTeamSelected(teamListModel.getTeamList().get(adapterPosition), adapterPosition);
            }
        });

        return teamListItemViewHolder;
    }

    @Override
    public void onBindViewHolder(TeamListItemViewHolder holder, int position) {
        holder.bind(teamListModel.getTeamList().get(position));
    }

    @Override
    public int getItemCount() {
        return teamListModel.getTeamList().size();
    }

    @Override
    public void onViewRecycled(TeamListItemViewHolder holder) {
        super.onViewRecycled(holder);
        holder.binding.teamCrestImageView.setImageDrawable(null);
        Glide.clear(holder.binding.teamCrestImageView);
    }

    static class TeamListItemViewHolder extends RecyclerView.ViewHolder {

        private final AdapterTeamListItemBinding binding;
        private final GlideRequestProvider glideRequestProvider;

        TeamListItemViewHolder(AdapterTeamListItemBinding binding, GlideRequestProvider glideRequestProvider) {
            super(binding.getRoot());
            this.binding = binding;
            this.glideRequestProvider = glideRequestProvider;
        }

        AdapterTeamListItemBinding getBinding() {
            return binding;
        }

        void bind(TeamsItem team) {
            binding.setTeam(team);
            Context context = binding.getRoot().getContext();

            if (team.getCrestUrl().contains(".svg")) {
                handleSvg(team, context);
            } else {
                handlePng(team);
            }
        }

        private void handlePng(TeamsItem team) {
            Glide.with(binding.getRoot().getContext())
                .load(team.getCrestUrl())
                .into(binding.teamCrestImageView);
        }

        private void handleSvg(TeamsItem team, Context context) {
            GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable>
                requestBuilder = glideRequestProvider.getGenericRequest(context);

            Uri uri = Uri.parse(team.getCrestUrl());

            FutureTarget<PictureDrawable> future = requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .dontAnimate()
                .load(uri)
                .into(48, 48);

            Observable.fromFuture(future)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PictureDrawable>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PictureDrawable pictureDrawable) {
                        binding.teamCrestImageView.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
                        binding.teamCrestImageView.setImageDrawable(pictureDrawable);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        }

    }


}
