package com.bltucker.transitiontutorial.teamlist;


import android.support.annotation.Nullable;

import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.TeamsItem;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class TeamListFragmentModule {

    @Nullable
    private final TeamListModel model;

    public TeamListFragmentModule(@Nullable TeamListModel model){
        this.model = model;
    }

    @Provides
    public TeamListModel provideModel(){
        return model != null ? model : new TeamListModel(0, false, null, true, new ArrayList<TeamsItem>());
    }
}
