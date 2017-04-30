package com.bltucker.transitiontutorial.teamlist;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bltucker.transitiontutorial.DaggerInjector;
import com.bltucker.transitiontutorial.data.TeamsItem;
import com.bltucker.transitiontutorial.databinding.FragmentTeamListBinding;
import com.bltucker.transitiontutorial.teamdetail.TeamDetailActivity;

import javax.inject.Inject;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;


public class TeamListFragment extends Fragment implements TeamListView{

    public static final String TAG = "teamListFragment";

    public static TeamListFragment newInstance(){
        return new TeamListFragment();
    }


    private FragmentTeamListBinding binding;

    public TeamListFragment() {
        // Required empty public constructor
    }


    @Inject
    TeamListViewPresenter presenter;

    @Inject
    TeamListAdapter teamListAdapter;

    @Inject
    TeamListModel teamListModel;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupDaggerComponent(context);
    }

    private void setupDaggerComponent(Context context) {
        TeamListModel retainedModel = null;

        if(context instanceof Activity){
            retainedModel = (TeamListModel) ((FragmentActivity) context).getLastCustomNonConfigurationInstance();
        }

        DaggerInjector.getApplicationComponent()
            .teamListFragmentComponentBuilder()
            .teamListFragmentModule(new TeamListFragmentModule(retainedModel))
            .teamListFragment(this)
            .build()
            .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            presenter.onViewRestored(this);
        } else {
            presenter.onViewCreated(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamListBinding.inflate(inflater, container, false);

        binding.setModel(teamListModel);

        binding.teamListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.teamListRecyclerView.setAdapter(teamListAdapter);
        binding.teamListRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResumed(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.onViewPaused();
    }

    @Nullable
    public TeamListModel getTeamListModel() {
        //consider removing this by promoting the model's scope to the activity
        //so that it can be accessed from elsewhere
        return teamListModel;
    }

    @Override
    public void displayModel(TeamListModel updatedModel) {
        binding.setModel(updatedModel);
        teamListModel = updatedModel;
        teamListAdapter.updateAdapter(updatedModel);
    }

    @Override
    public void showTeamDetails(TeamsItem teamsItem, int teamIndex) {
        TeamListAdapter.TeamListItemViewHolder viewHolder = (TeamListAdapter.TeamListItemViewHolder) binding.teamListRecyclerView.findViewHolderForAdapterPosition(teamIndex);

        ImageView teamCrestImageView = viewHolder.getBinding().teamCrestImageView;
        TextView teamNameTextView = viewHolder.getBinding().teamNameTextview;

        Pair[] sharedElements = new Pair[]{new Pair<View,String>(teamCrestImageView, teamCrestImageView.getTransitionName()),
        new Pair<View,String>(teamNameTextView, teamNameTextView.getTransitionName())};
        ActivityOptionsCompat options = makeSceneTransitionAnimation(getActivity(), sharedElements);

        Intent launchIntent = TeamDetailActivity.getLaunchIntent(getActivity(), teamsItem);
        startActivity(launchIntent, options.toBundle());
    }

}
