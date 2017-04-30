package com.bltucker.transitiontutorial.teamdetail;


import com.bltucker.transitiontutorial.data.PlayersItem;
import com.bltucker.transitiontutorial.data.TeamsItem;

import java.util.List;

public class TeamDetailModel {

    private final TeamsItem teamsItem;

    private final List<PlayersItem> playerList;

    TeamDetailModel(TeamsItem teamsItem, List<PlayersItem> players){
        this.teamsItem = teamsItem;
        this.playerList = players;
    }

    TeamsItem getTeamsItem() {
        return teamsItem;
    }

    List<PlayersItem> getPlayerList() {
        return playerList;
    }
}
