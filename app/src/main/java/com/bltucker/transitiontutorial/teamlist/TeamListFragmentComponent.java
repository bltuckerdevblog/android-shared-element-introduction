package com.bltucker.transitiontutorial.teamlist;


import com.bltucker.transitiontutorial.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {TeamListFragmentModule.class})
public interface TeamListFragmentComponent {

    void inject(TeamListFragment teamListFragment);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance Builder teamListFragment(TeamListFragment fragment);
        TeamListFragmentComponent.Builder teamListFragmentModule(TeamListFragmentModule module);
        TeamListFragmentComponent build();
    }
}
