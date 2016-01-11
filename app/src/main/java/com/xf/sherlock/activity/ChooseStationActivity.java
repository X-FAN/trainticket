package com.xf.sherlock.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.adapter.StationAdapter;
import com.xf.sherlock.bean.Station;
import com.xf.sherlock.event.ChooseStationEvent;
import com.xf.sherlock.request.GetTrainStationService;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;
import com.xf.sherlock.widget.SideBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class ChooseStationActivity extends BaseActivity {

    private GetTrainStationService mGetTrainStationService;
    private StationAdapter mStationAdapter;
    private Filter mFilter;
    private List<Station> mStationList;

    private SideBar mSideBar;
    private ListView mStationShow;
    private EditText mFrom;
    private EditText mTo;
    private Station mFromStation;
    private Station mToStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_station);
        initViews();
        addListener();
        initData();

    }

    private void initViews() {
        initToolBar();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setTitle("选择车站");
        mStationShow = (ListView) findViewById(R.id.station_show);
        mSideBar = (SideBar) findViewById(R.id.sidebar);
        TextView tip = (TextView) findViewById(R.id.toast_text);
        mFrom = (EditText) findViewById(R.id.from);
        mTo = (EditText) findViewById(R.id.to);
        mSideBar.setTextView(tip);
    }

    private void addListener() {
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // TODO Auto-generated method stub
                if (mStationAdapter != null) {
                    // 该字母首次出现的位置
                    int position = mStationAdapter.getPositionForSection(s);
                    if (position != -1) {
                        mStationShow.setSelection(position);
                    }
                }
            }
        });
        RxTextView.textChanges(mFrom)
                .compose(this.<CharSequence>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        filterStation(charSequence);

                    }
                });
        RxTextView.textChanges(mTo)
                .compose(this.<CharSequence>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        filterStation(charSequence);
                    }
                });
        RxToolbar.navigationClicks(mToolbar)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().post(new ChooseStationEvent(mFromStation, mToStation));
                        finish();
                    }
                });
        RxAdapterView.itemClicks(mStationShow)
                .compose(this.<Integer>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Station station = mStationAdapter.getItem(integer);
                        if (mFrom.isFocused()) {
                            mFrom.setText(station.getStationName());
                            mFromStation = station;
                        } else if (mTo.isFocused()) {
                            mTo.setText(station.getStationName());
                            mToStation = station;
                        }
                    }
                });
    }

    private void initData() {
        mGetTrainStationService = RetrofitUtils.getInstance(this).create(GetTrainStationService.class);
        mGetTrainStationService.getTrainStation()
                .compose(this.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, List<Station>>() {
                    @Override
                    public List<Station> call(String s) {
                        return paraStation(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Station>>() {
                    @Override
                    public void call(List<Station> stations) {
                        mStationAdapter = new StationAdapter(ChooseStationActivity.this, stations);
                        mFilter = mStationAdapter.getFilter();
                        mStationShow.setAdapter(mStationAdapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        T.showShort(ChooseStationActivity.this, throwable.getMessage());
                    }
                });
    }


    //根据输入关键字,对车站进行过滤
    private void filterStation(CharSequence charSequence) {
        if (mFilter != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                mFilter.filter(charSequence.toString().trim());
                mSideBar.setVisibility(View.GONE);
            } else {
                mFilter.filter(null);
                mSideBar.setVisibility(View.VISIBLE);
            }
        }
    }

    //解析车站信息
    @NonNull
    private List<Station> paraStation(String s) {
        s = s.substring("var station_names ='@".length());
        String[] src = s.split("@");
        final StringBuilder recordSection = new StringBuilder();
        Observable.from(src)
                .map(new Func1<String, Station>() {
                    @Override
                    public Station call(String stationInfo) {//解析车站信息
                        String[] stationArray = stationInfo.split("\\|");
                        String section = stationArray[0].substring(0, 1);
                        Station station = new Station();
                        station.setShortPY(stationArray[0]);
                        station.setSection(section.toUpperCase());
                        station.setStationName(stationArray[1]);
                        station.setStationCode(stationArray[2]);
                        station.setFullPY(stationArray[3]);
                        if (!recordSection.toString().contains(section)) {
                            station.setIsShow(true);
                            recordSection.append(section);
                        }
                        return station;
                    }
                })
                .toSortedList(new Func2<Station, Station, Integer>() {//排序
                    @Override
                    public Integer call(Station station, Station station2) {
                        return station.getSection().compareTo(station2.getSection());
                    }
                })
                .subscribe(new Action1<List<Station>>() {
                    @Override
                    public void call(List<Station> stations) {
                        mStationList = stations;
                    }
                });
        return mStationList;
    }


}
