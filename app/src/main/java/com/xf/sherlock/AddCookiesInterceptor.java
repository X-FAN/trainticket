package com.xf.sherlock;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashSet;

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        HashSet<String> cookieSet = (HashSet<String>) sharedPreferences.getStringSet("cookie", new HashSet<String>());
        for (String cookie : cookieSet) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}