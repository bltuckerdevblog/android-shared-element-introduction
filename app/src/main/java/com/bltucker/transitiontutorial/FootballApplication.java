package com.bltucker.transitiontutorial;


import android.app.Application;

public class FootballApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();

    }

    private void setupDagger() {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build();

        DaggerInjector.initializeInjector(applicationComponent);
    }
}
