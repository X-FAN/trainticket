package com.xf.sherlock.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashSet;

import rx.Observable;
import rx.functions.Action1;

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final HashSet<String> cookies = new HashSet<>();
            Observable.from(originalResponse.headers("Set-Cookie")).subscribe(new Action1<String>() {
                @Override
                public void call(String cookie) {
                    cookies.add(cookie);
                }
            });
            SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            sharedPreferences.edit().putStringSet("cookie", cookies);
            sharedPreferences.edit().apply();
        }

        return originalResponse;
    }
}