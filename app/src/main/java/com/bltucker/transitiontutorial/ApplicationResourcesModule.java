package com.bltucker.transitiontutorial;


import android.content.Context;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
@Singleton
class ApplicationResourcesModule {

    @Provides
    @Singleton
    Resources provideApplicationResources(FootballApplication application){
        return application.getResources();
    }

    @Provides
    @Singleton
    Context provideApplicationContext(FootballApplication application){
        return application.getApplicationContext();
    }

    @Provides
    @Named("ApiKey")
    @Singleton
    String provideApiKey(){
        return BuildConfig.FOOTBALL_DATA_API_KEY;
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(RequestHeaderModificationInterceptor interceptor){
        return new OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
            .baseUrl("http://football-data.org/v1/")
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    FootballDataApi provideFootballDataApi(Retrofit retrofit){
        return retrofit.create(FootballDataApi.class);
    }

}
