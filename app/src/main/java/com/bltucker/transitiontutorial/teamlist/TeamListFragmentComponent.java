package com.bltucker.transitiontutorial.teamlist;


import com.bltucker.transitiontutorial.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {TeamListFragmentModule.class})
public interface TeamListActivityComponent {


    @Subcomponent.Builder
    interface Builder {
        @BindsInstance Builder teamListActivity(TeamListActivity activity);
        TeamListActivityComponent build();
    }
}
