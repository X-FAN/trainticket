package com.xf.sherlock;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.xf.sherlock.utils.RetrofitUtils;

/**
 * Created by Administrator on 2015/12/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置Picasso
        Picasso.setSingletonInstance(new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build());
    }
}