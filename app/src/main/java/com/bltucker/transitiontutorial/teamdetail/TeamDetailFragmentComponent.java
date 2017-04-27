package com.bltucker.transitiontutorial.teamdetail;


import com.bltucker.transitiontutorial.FragmentScope;
import com.bltucker.transitiontutorial.data.TeamsItem;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent
public interface TeamDetailFragmentComponent {

    void inject(TeamDetailFragment teamDetailFragment);


    @Subcomponent.Builder
    interface Builder{
        @BindsInstance TeamDetailFragmentComponent.Builder bindTeam(TeamsItem teamsItem);
        TeamDetailFragmentComponent build();
    }

}
