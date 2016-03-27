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
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.greendao.Station;
import com.xf.sherlock.MyConstant;
import com.xf.sherlock.R;
import com.xf.sherlock.animator.RollAnimator;
import com.xf.sherlock.bean.QueryCondition;
import com.xf.sherlock.event.ChooseDateEvent;
import com.xf.sherlock.event.ChooseStationEvent;
import com.xf.sherlock.event.SendCurrentDateEvent;
import com.xf.sherlock.event.SendQueryConditionEvent;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.T;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends BaseActivity {


    private TextView mFromStation;
    private TextView mToStation;
    private Button mQuery;//查询按钮
    private TextView mDate;//出发日期
    private ImageView mSwitch;
    @Bind(R.id.date)
    TextView mChooseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        Calendar todayCal = Calendar.getInstance();
        setDate(CalendarDay.from(todayCal));
        mDate.setTag(CalendarDay.from(todayCal));
        mSwitch = (ImageView) findViewById(R.id.switch_button);
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
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.login://跳转到登录界面
                        CommonUtils.jump(MainActivity.this, LoginActivity.class);
                        break;
                    case R.id.unfinished_order://跳转到未完成订单页面
                        CommonUtils.jump(MainActivity.this, UnfinishedOrderActivity.class);
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
                    public void call(Void aVoid) {//添加动画
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
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (check()) {
                            Station fromStation = (Station) mFromStation.getTag();
                            Station toStation = (Station) mToStation.getTag();
                            Calendar date = ((CalendarDay) mDate.getTag()).getCalendar();
                            EventBus.getDefault().postSticky(new SendQueryConditionEvent(new QueryCondition(fromStation, toStation, date)));
                            CommonUtils.jump(MainActivity.this, QueryResultActivity.class);
                        }
                    }
                });

        //选择出发日期
        RxView.clicks(mChooseDate)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        CalendarDay calendarDay = (CalendarDay) mDate.getTag();
                        EventBus.getDefault().postSticky(new SendCurrentDateEvent(calendarDay));
                        CommonUtils.jump(MainActivity.this, DatePickActivity.class);
                    }
                });


    }

    //检验输入的条件
    private boolean check() {
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
            return true;
        }
        return false;
    }

    //更新UI
    public void onEventMainThread(Object event) {
        if (event instanceof ChooseStationEvent) {
            handChooseStationEvent((ChooseStationEvent) event);
            EventBus.getDefault().removeStickyEvent(event);
        } else if (event instanceof ChooseDateEvent) {
            handChooseDateEvent((ChooseDateEvent) event);
            EventBus.getDefault().removeStickyEvent(event);
        }

    }

    private void handChooseDateEvent(ChooseDateEvent event) {
        ChooseDateEvent chooseDateEvent = event;
        final CalendarDay selectedCal = chooseDateEvent.getCalendarDay();
        setDate(selectedCal);
    }

    private void setDate(final CalendarDay selectedCal) {

        rx.Observable.just(selectedCal).map(new Func1<CalendarDay, String>() {
            @Override
            public String call(CalendarDay calendarDay) {//处理日期格式
                return CommonUtils.getDate(calendarDay.getCalendar());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mDate.setText(s);
            }
        });

        mDate.setTag(selectedCal);
    }

    private void handChooseStationEvent(ChooseStationEvent event) {
        ChooseStationEvent chooseStationEvent = event;
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
