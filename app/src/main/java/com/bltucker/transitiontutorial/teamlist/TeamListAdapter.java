package com.bltucker.transitiontutorial.teamlist;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.TeamsItem;
import com.bltucker.transitiontutorial.databinding.AdapterTeamListItemBinding;
import com.bltucker.transitiontutorial.glide.SvgDecoder;
import com.bltucker.transitiontutorial.glide.SvgDrawableTranscoder;
import com.bltucker.transitiontutorial.glide.SvgSoftwareLayerSetter;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import javax.inject.Inject;

@FragmentScope
class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamListItemViewHolder> {

    private TeamListModel teamListModel;

    @Inject
    TeamListAdapter(TeamListModel teamListModel) {
        this.teamListModel = teamListModel;
    }

    void updateAdapter(TeamListModel model) {
        this.teamListModel = model;
        notifyDataSetChanged();
        //TODO do a diff here
    }

    @Override
    public TeamListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterTeamListItemBinding binding = AdapterTeamListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TeamListItemViewHolder(binding);
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

        TeamListItemViewHolder(AdapterTeamListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
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
                requestBuilder = Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter());

            Uri uri = Uri.parse(team.getCrestUrl());
            requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(binding.teamCrestImageView);
        }

    }


}
