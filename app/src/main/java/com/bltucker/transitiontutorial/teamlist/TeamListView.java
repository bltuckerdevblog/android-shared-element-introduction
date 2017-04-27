package com.bltucker.transitiontutorial.teamlist;


import com.bltucker.transitiontutorial.data.TeamsItem;

interface TeamListView {
    void displayModel(TeamListModel updatedModel);

    void showTeamDetails(TeamsItem teamsItem, int teamIndex);
}
