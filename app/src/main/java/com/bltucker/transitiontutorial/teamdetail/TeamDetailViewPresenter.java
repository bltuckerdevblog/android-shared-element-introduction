package com.bltucker.transitiontutorial.teamdetail;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bltucker.transitiontutorial.FootballDataApi;
import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.PlayerListResponse;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@FragmentScope
class TeamDetailViewPresenter {

    private TeamDetailModel detailModel;
    private final FootballDataApi api;
    @Nullable
    private
    TeamDetailView presentedView;

    @Inject
    TeamDetailViewPresenter(TeamDetailModel detailModel, FootballDataApi api) {
        this.detailModel = detailModel;
        this.api = api;
    }


    void onViewCreated(@NonNull TeamDetailView teamDetailView) {
        presentedView = teamDetailView;
        presentedView.displayModel(detailModel);

        api.getPlayerList(detailModel.getTeamsItem().getId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<PlayerListResponse>() {
                @Override
                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@io.reactivex.annotations.NonNull PlayerListResponse playerListResponse) {
                    if(presentedView != null){
                        detailModel = new TeamDetailModel(detailModel.getTeamsItem(), playerListResponse.getPlayers());
                        presentedView.displayModel(detailModel);
                    }
                }

                @Override
                public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                    Timber.e(e, "Error getting player list");
                }
            });
    }

    void onViewResumed(@NonNull TeamDetailView teamDetailView) {
        presentedView = teamDetailView;
    }

    void onViewPaused() {
        presentedView = null;
    }

}
