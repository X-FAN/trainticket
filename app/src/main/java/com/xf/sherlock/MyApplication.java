package com.xf.sherlock;

import android.app.Application;

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
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");

    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build();
        Picasso.setSingletonInstance(picasso);
    }
}
