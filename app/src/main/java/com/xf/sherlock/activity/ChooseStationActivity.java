package com.xf.sherlock.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.EditText;
import android.widget.ListView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.adapter.StationAdapter;
import com.xf.sherlock.bean.Station;
import com.xf.sherlock.request.GetTrainStationService;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;
import com.xf.sherlock.widget.SideBar;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChooseStationActivity extends BaseActivity {

    private GetTrainStationService mGetTrainStationService;
    private StationAdapter mStationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_station);
        initToolBar();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setTitle("选择车站");
        final ListView stationShow = (ListView) findViewById(R.id.station_show);
        SideBar sideBar = (SideBar) findViewById(R.id.sidebar);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // TODO Auto-generated method stub
                if (mStationAdapter != null) {
                    // 该字母首次出现的位置
                    int position = mStationAdapter.getPositionForSection(s);
                    if (position != -1) {
                        stationShow.setSelection(position);
                    }
                }
            }
        });
        EditText from = (EditText) findViewById(R.id.from);
        EditText to = (EditText) findViewById(R.id.to);
        RxTextView.textChanges(from)
                .compose(this.<CharSequence>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                    }
                });
        RxTextView.textChanges(to)
                .compose(this.<CharSequence>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {

                    }
                });
  /*      RxTextView.afterTextChangeEvents(from)
                .compose(this.<TextViewAfterTextChangeEvent>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {

                    }
                });
        RxTextView.afterTextChangeEvents(to)
                .compose(this.<TextViewAfterTextChangeEvent>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {

                    }
                });*/

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
                        stationShow.setAdapter(mStationAdapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        T.showShort(ChooseStationActivity.this, throwable.getMessage());
                    }
                });


    }

    //解析车站信息
    @NonNull
    private List<Station> paraStation(String s) {
        s = s.substring("var station_names ='@".length());
        List<Station> stationList = new ArrayList<>();
        String[] src = s.split("@");
        String recordSection = "sign";
        for (String stationInfo : src) {
            String[] stationArray = stationInfo.split("\\|");
            String section = stationArray[0].substring(0, 1);
            Station station = new Station();
            station.setShortPY(stationArray[0]);
            station.setSection(section.toUpperCase());
            station.setStationName(stationArray[1]);
            station.setStationCode(stationArray[2]);
            station.setFullPY(stationArray[3]);
            if (!section.equals(recordSection)) {
                station.setIsShow(true);
                recordSection = section;
            }
            stationList.add(station);
        }
        return stationList;
    }

}
