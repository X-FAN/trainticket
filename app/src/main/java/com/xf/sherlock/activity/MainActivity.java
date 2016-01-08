package com.xf.sherlock.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.event.ChooseStationEvent;
import com.xf.sherlock.request.QueryService;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    private QueryService mQueryService;

    private TextView mFromStation;
    private TextView mToStation;
    private Button mQuery;//查询按钮
    private TextView mDate;//出发日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        cleanCookies();
        super.onDestroy();
    }

    //清除保存的cookie
    private void cleanCookies() {
        SharedPreferences sharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


    private void initViews() {
        initToolBar();
        setTitle("查询");
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        RelativeLayout head = (RelativeLayout) findViewById(R.id.head);
        mFromStation = (TextView) findViewById(R.id.from);
        mToStation = (TextView) findViewById(R.id.to);
        mQuery = (Button) findViewById(R.id.query);
        mDate = (TextView) findViewById(R.id.date);
        mQueryService = RetrofitUtils.getInstance(this).create(QueryService.class);
        addListener(drawerLayout, navigationView, head);
    }

    private void addListener(final DrawerLayout drawerLayout, NavigationView navigationView, RelativeLayout head) {
        RxView.clicks(head)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        CommonUtils.jump(MainActivity.this, ChooseStationActivity.class);
                    }
                });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.login://跳转到登录界面
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        CommonUtils.jump(MainActivity.this, LoginActivity.class);
                        break;
                    default:
                        break;

                }

                return true;
            }
        });
        RxView.clicks(mQuery)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        String fromStation = mFromStation.getText().toString();
                        String toStation = mToStation.getText().toString();
                        String date = mDate.getText().toString();
                        if (TextUtils.isEmpty(fromStation)) {
                            T.showShort(MainActivity.this, "请选择出发车站");
                        } else if (TextUtils.isEmpty(toStation)) {
                            T.showShort(MainActivity.this, "请选择到达车站");
                        } else if (fromStation.equals(toStation)) {
                            T.showShort(MainActivity.this, "出发车站与到达车站相同");
                        } else if (TextUtils.isEmpty(date)) {
                            T.showShort(MainActivity.this, "请选择出发日期");
                        }
                        mQueryService.getCookie();

                    }
                });
    }

 /*   private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(RetrofitUtils.getClient(this))).build();
        picasso.setIndicatorsEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }*/

    //更新选择的车站
    public void onEventMainThread(ChooseStationEvent chooseStationEvent) {
        if (chooseStationEvent.getFromStation() != null) {
            mFromStation.setText(chooseStationEvent.getFromStation().getStationName());
        }
        if (chooseStationEvent.getToStaion() != null) {
            mToStation.setText(chooseStationEvent.getToStaion().getStationName());
        }
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
