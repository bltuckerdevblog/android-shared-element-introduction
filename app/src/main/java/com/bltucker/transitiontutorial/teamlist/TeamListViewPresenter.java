package com.bltucker.transitiontutorial.teamlist;


import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.FootballDataApi;
import com.bltucker.transitiontutorial.data.TeamListResponse;
import com.bltucker.transitiontutorial.data.TeamsItem;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@FragmentScope
public class TeamListViewPresenter {

    private TeamListModel teamListModel;
    private final FootballDataApi footballDataApi;

    @Nullable
    private TeamListView presentedView;
    private boolean updateOnResume;

    @Inject
    TeamListViewPresenter(TeamListModel teamListModel, FootballDataApi footballDataApi){
        this.teamListModel = teamListModel;
        this.footballDataApi = footballDataApi;
    }


    void onViewCreated(TeamListView view){
        presentedView = view;
        presentedView.displayModel(teamListModel);
        loadTeamList();
    }

    private void loadTeamList() {
        final long syncTime = System.currentTimeMillis();
        this.footballDataApi.getTeamList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new SingleObserver<TeamListResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) { }

                @Override
                public void onSuccess(@NonNull TeamListResponse teamListResponse) {
                    if(presentedView != null){
                        teamListModel = new TeamListModel(syncTime, false, null, false, teamListResponse.getTeams());
                        presentedView.displayModel(teamListModel);
                    } else{
                        updateOnResume = true;
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Timber.e(e, "Error retrieving team list");

                    if(presentedView != null){
                        TeamListModel updatedModel = new TeamListModel(syncTime, true, "Error getting team list", false, new ArrayList<TeamsItem>());
                        presentedView.displayModel(updatedModel);
                    }
                }
            });
    }

    void onViewRestored(TeamListView view){
        this.presentedView = view;
    }

    void onViewResumed(TeamListView view){
        this.presentedView = view;
        if(updateOnResume){
            presentedView.displayModel(teamListModel);
            updateOnResume = false;
        }
    }

    void onViewPaused(){
        this.presentedView = null;
    }


    void onTeamSelected(TeamsItem teamsItem, int teamIndex) {
        if(presentedView != null){
            presentedView.showTeamDetails(teamsItem, teamIndex);
        }
    }
}
