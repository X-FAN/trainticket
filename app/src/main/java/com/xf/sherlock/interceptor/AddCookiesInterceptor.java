package com.xf.sherlock.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashSet;

import rx.Observable;
import rx.functions.Action1;

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        HashSet<String> cookieSet = (HashSet<String>) sharedPreferences.getStringSet("cookie", new HashSet<String>());
        Observable.from(cookieSet).subscribe(new Action1<String>() {
            @Override
            public void call(String cookie) {
                builder.addHeader("Cookie", cookie);
            }
        });
        return chain.proceed(builder.build());
    }
}