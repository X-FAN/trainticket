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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jakewharton.rxbinding.view.RxView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.MyConstant;
import com.xf.sherlock.R;
import com.xf.sherlock.animator.RollAnimator;
import com.xf.sherlock.bean.Station;
import com.xf.sherlock.bean.TrainTicketResult;
import com.xf.sherlock.event.ChooseStationEvent;
import com.xf.sherlock.request.QueryService;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.L;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private QueryService mQueryService;
    private Observable<TrainTicketResult> mTrainTicketResultOb;
    private TextView mFromStation;
    private TextView mToStation;
    private Button mQuery;//查询按钮
    private TextView mDate;//出发日期
    private ImageView mSwitch;

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
        mSwitch = (ImageView) findViewById(R.id.switch_button);
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

        RxView.clicks(mSwitch)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mQuery.setEnabled(false);
                        YoYo.with(new RollAnimator()).duration(MyConstant.ANI_TIME).playOn(mSwitch);
                        YoYo.with(Techniques.FlipOutX).duration(MyConstant.ANI_TIME).withListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                String temp = mFromStation.getText().toString();
                                Station tempStation = (Station) mFromStation.getTag();
                                mFromStation.setText(mToStation.getText());
                                mFromStation.setTag(mToStation.getTag());
                                mToStation.setText(temp);
                                mToStation.setTag(tempStation);
                                YoYo.with(Techniques.FlipInX).duration(MyConstant.ANI_TIME).withListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        mQuery.setEnabled(true);
                                    }
                                }).playOn(mFromStation);
                            }
                        }).playOn(mFromStation);
                        YoYo.with(Techniques.FlipOutX).duration(MyConstant.ANI_TIME).withListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                YoYo.with(Techniques.FlipInX).duration(MyConstant.ANI_TIME).playOn(mToStation);
                            }
                        }).playOn(mToStation);
                    }

                });

        //查询
        RxView.clicks(mQuery)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .flatMap(new Func1<Void, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(Void aVoid) {
                        L.e(Thread.currentThread().getName());
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
                        } else {
                            return mQueryService.getCookie().compose(MainActivity.this.<Void>bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io());
                        }
                        return null;

                    }
                })
                .flatMap(new Func1<Void, Observable<String>>() {

                    @Override
                    public Observable<String> call(Void aVoid) {
                        return mQueryService.getTrainTicketResult("queryT", "2016-01-18", "YZV", "ZAF", "ADULT");
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e(s);
                        L.e(Thread.currentThread().getName());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        L.e("error:" + throwable.getMessage());
                        L.e(Thread.currentThread().getName());
                    }
                });


    }

    //更新选择的车站
    public void onEventMainThread(ChooseStationEvent chooseStationEvent) {
        if (chooseStationEvent.getFromStation() != null) {
            mFromStation.setText(chooseStationEvent.getFromStation().getStationName());
            mFromStation.setTag(chooseStationEvent.getFromStation());
        }
        if (chooseStationEvent.getToStaion() != null) {
            mToStation.setText(chooseStationEvent.getToStaion().getStationName());
            mToStation.setTag(chooseStationEvent.getToStaion());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
