package com.bltucker.transitiontutorial;

import com.bltucker.transitiontutorial.teamdetail.TeamDetailFragmentComponent;
import com.bltucker.transitiontutorial.teamlist.TeamListFragmentComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
    modules = {ApplicationResourcesModule.class,
                SubcomponentModule.class})
public interface ApplicationComponent {

    TeamListFragmentComponent.Builder teamListFragmentComponentBuilder();
    TeamDetailFragmentComponent.Builder teamDetailFragmentComponentBuilder();


    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(FootballApplication application);
        ApplicationComponent build();
    }

}

