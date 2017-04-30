package com.bltucker.transitiontutorial.teamdetail;


import com.bltucker.transitiontutorial.data.PlayersItem;
import com.bltucker.transitiontutorial.data.TeamsItem;

import java.util.List;

class TeamDetailModel {

    private final TeamsItem teamsItem;

    private final List<PlayersItem> playerList;

    public TeamDetailModel(TeamsItem teamsItem, List<PlayersItem> players){
        this.teamsItem = teamsItem;
        this.playerList = players;
    }

    public TeamsItem getTeamsItem() {
        return teamsItem;
    }

    public List<PlayersItem> getPlayerList() {
        return playerList;
    }
}
