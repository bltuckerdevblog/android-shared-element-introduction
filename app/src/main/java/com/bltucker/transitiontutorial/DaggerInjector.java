package com.bltucker.transitiontutorial;


public class DaggerInjector {

    private static ApplicationComponent applicationComponent;

    static void initializeInjector(ApplicationComponent applicationComponent){
        DaggerInjector.applicationComponent = applicationComponent;
    }


    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

}
