package com.xf.sherlock;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.xf.sherlock.utils.L;
import com.xf.sherlock.utils.RetrofitUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2015/12/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        L.isDebug = true;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        initPicasso();
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build();
        Picasso.setSingletonInstance(picasso);
    }
}
