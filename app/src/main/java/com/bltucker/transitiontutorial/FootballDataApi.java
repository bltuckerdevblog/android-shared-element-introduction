package com.bltucker.transitiontutorial;


import com.bltucker.transitiontutorial.data.PlayerListResponse;
import com.bltucker.transitiontutorial.data.TeamListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FootballDataApi {

    @GET("competitions/426/teams")
    Single<TeamListResponse> getTeamList();

    @GET("teams/{teamId}/players")
    Single<PlayerListResponse> getPlayerList(@Path("teamId") int teamId);
}
