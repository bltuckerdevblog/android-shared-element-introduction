package com.bltucker.transitiontutorial.teamlist;


import android.support.annotation.Nullable;

import com.bltucker.transitiontutorial.ActivityScope;
import com.bltucker.transitiontutorial.FootballDataApi;
import com.bltucker.transitiontutorial.data.TeamListResponse;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@ActivityScope
public class TeamListPresenter {

    private final TeamListModel teamListModel;
    private final FootballDataApi footballDataApi;

    @Nullable
    private TeamListView presentedView;

    @Inject
    public TeamListPresenter(TeamListModel teamListModel, FootballDataApi footballDataApi){
        this.teamListModel = teamListModel;
        this.footballDataApi = footballDataApi;
    }


    void onViewCreated(TeamListView view){
        this.presentedView = view;

        this.footballDataApi.getTeamList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new SingleObserver<TeamListResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull TeamListResponse teamListResponse) {
                    //TODO

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Timber.e(e, "Error retrieving team list");
                }
            });




    }

    void onViewRestored(TeamListView view){
        this.presentedView = view;
    }

    void onViewResumed(TeamListView view){
        this.presentedView = view;
    }

    void onViewPaused(){
        this.presentedView = null;
    }

}
