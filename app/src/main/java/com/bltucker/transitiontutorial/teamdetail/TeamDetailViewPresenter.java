package com.bltucker.transitiontutorial.teamdetail;


import android.support.annotation.Nullable;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.TeamsItem;

import javax.inject.Inject;

@FragmentScope
class TeamDetailViewPresenter {

    private final TeamsItem teamsItem;
    @Nullable
    TeamDetailView presentedView;

    @Inject
    TeamDetailViewPresenter(TeamsItem teamsItem) {
        this.teamsItem = teamsItem;
    }


    public void onViewCreated(TeamDetailView teamDetailView) {
        presentedView = teamDetailView;

        presentedView.displayTeamDetails(teamsItem);
    }

    public void onViewResumed(TeamDetailView teamDetailView) {
        presentedView = teamDetailView;
    }

    public void onViewPaused() {
        presentedView = null;
    }

}
