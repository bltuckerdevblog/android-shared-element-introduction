package com.bltucker.transitiontutorial;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class RequestHeaderModificationInterceptor implements Interceptor {

    private final String apiKey;

    @Inject
    public RequestHeaderModificationInterceptor(@Named("ApiKey") String apiKey){
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", apiKey)
            .addHeader("X-Response-Control", "minified")
            .build();

        return chain.proceed(request);
    }
}
