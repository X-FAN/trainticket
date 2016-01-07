package com.xf.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.RetrofitUtils;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initToolBar();
        setTitle("查询");
        initViews();
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.login://跳转到登录界面
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }

                return true;
            }
        });

        //设置Picasso
        initPicasso();
    }

    private void initViews() {
        RelativeLayout head = (RelativeLayout) findViewById(R.id.head);
        RxView.clicks(head)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        CommonUtils.jump(MainActivity.this, ChooseStationActivity.class);
                    }
                });
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build();
        picasso.setIndicatorsEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
