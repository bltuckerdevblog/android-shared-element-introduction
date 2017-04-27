package com.bltucker.transitiontutorial;


import android.content.Context;
import android.content.res.Resources;

import com.bltucker.transitiontutorial.glide.SvgDecoder;
import com.bltucker.transitiontutorial.glide.SvgDrawableTranscoder;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    FootballDataApi provideFootballDataApi(Retrofit retrofit){
        return retrofit.create(FootballDataApi.class);
    }

    @Provides
    @Singleton
    SvgDecoder provideSvgDecoder(){
        return new SvgDecoder();
    }

    @Provides
    @Singleton
    FileToStreamDecoder<SVG> provideFileToStreamDecoder(SvgDecoder svgDecoder){
        return new FileToStreamDecoder<>(svgDecoder);
    }

    @Provides
    @Singleton
    StreamEncoder provideStreamEncoder(){
        return new StreamEncoder();
    }

    @Provides
    @Singleton
    SvgDrawableTranscoder provideSvgDrawableTranscoder(){
        return new SvgDrawableTranscoder();
    }
}
