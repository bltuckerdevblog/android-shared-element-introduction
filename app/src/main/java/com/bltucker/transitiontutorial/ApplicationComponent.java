package com.bltucker.transitiontutorial;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
    modules = {ApplicationResourcesModule.class,
                SubcomponentModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(FootballApplication application);
        ApplicationComponent build();
    }

}

