package com.bltucker.transitiontutorial;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {ApplicationResourcesModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(FootballApplication application);
        ApplicationComponent build();
    }

}

