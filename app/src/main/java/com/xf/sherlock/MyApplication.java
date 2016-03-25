package com.xf.sherlock;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.PlatformConfig;
import com.xf.sherlock.utils.L;
import com.xf.sherlock.utils.RetrofitUtils;

/**
 * Created by Administrator on 2015/12/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        L.isDebug = true;
        initPicasso();
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("1515402142", "048774ac7f421a54ae800237ec5693fd");
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx3e2c0cebfe42f6ee", "5b9fbf49cc01ced0f5e2ff5d79cce6f5");
        initStetho();

    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(
                                    Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(
                                    Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build();
        Picasso.setSingletonInstance(picasso);
    }
}
