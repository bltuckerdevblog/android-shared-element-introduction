package com.bltucker.transitiontutorial.teamdetail;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.PlayersItem;
import com.bltucker.transitiontutorial.databinding.AdapterPlayerListItemBinding;

import javax.inject.Inject;

@FragmentScope
class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    TeamDetailModel teamDetailModel;

    @Inject
    PlayerListAdapter(TeamDetailModel teamDetailModel) {
        this.teamDetailModel = teamDetailModel;
    }

    void updateModel(TeamDetailModel detailModel){
        this.teamDetailModel = detailModel;
        notifyDataSetChanged();
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterPlayerListItemBinding binding = AdapterPlayerListItemBinding.inflate(inflater, parent, false);

        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bind(teamDetailModel.getPlayerList().get(position));
    }

    @Override
    public int getItemCount() {
        return teamDetailModel.getPlayerList().size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {

        private final AdapterPlayerListItemBinding binding;

        PlayerViewHolder(AdapterPlayerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PlayersItem playersItem) {
            binding.setPlayerItem(playersItem);
        }
    }
}
