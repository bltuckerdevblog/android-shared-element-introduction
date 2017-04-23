package com.bltucker.transitiontutorial.teamlist;


import com.bltucker.transitiontutorial.ActivityScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {TeamListActivityModule.class})
public interface TeamListActivityComponent {


    @Subcomponent.Builder
    interface Builder {
        @BindsInstance Builder teamListActivity(TeamListActivity activity);
        TeamListActivityComponent build();
    }
}
