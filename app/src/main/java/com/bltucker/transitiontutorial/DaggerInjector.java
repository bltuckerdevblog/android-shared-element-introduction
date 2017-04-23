package com.bltucker.transitiontutorial;


public class DaggerInjector {

    private static ApplicationComponent applicationComponent;

    static void initializeInjector(ApplicationComponent applicationComponent){
        DaggerInjector.applicationComponent = applicationComponent;
    }


    public static ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

}
