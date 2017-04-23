package com.bltucker.transitiontutorial.teamlist;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bltucker.transitiontutorial.data.TeamsItem;

import java.util.List;

public class TeamListModel {

    //last sync time
    private final long lastSyncTime;

    private final boolean isError;

    @Nullable
    private final String errorMessage;

    private final boolean isLoading;

    private final List<TeamsItem> teamList;

    TeamListModel(long lastSyncTime, boolean isError, @Nullable String errorMessage, boolean isLoading, @NonNull List<TeamsItem> teamList) {
        this.lastSyncTime = lastSyncTime;
        this.isError = isError;
        this.errorMessage = errorMessage;
        this.isLoading = isLoading;
        this.teamList = teamList;
    }


    public long getLastSyncTime() {
        return lastSyncTime;
    }

    public boolean isError() {
        return isError;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public List<TeamsItem> getTeamList() {
        return teamList;
    }
}
