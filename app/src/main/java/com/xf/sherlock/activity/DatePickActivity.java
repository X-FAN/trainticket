package com.xf.sherlock.activity;

import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.xf.sherlock.R;
import com.xf.sherlock.event.ChooseDateEvent;
import com.xf.sherlock.event.SendCurrentDateEvent;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class DatePickActivity extends BaseActivity {

    @Bind(R.id.calendarView)
    MaterialCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pick);
        ButterKnife.bind(this);
        EventBus.getDefault().registerSticky(this);
        initToolBar();
        setTitle("选择日期");
        setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish() {
                CalendarDay selectedCal = mCalendarView.getSelectedDate();
                EventBus.getDefault().post(new ChooseDateEvent(selectedCal));
            }
        });
        Calendar minCal = Calendar.getInstance();
        mCalendarView.setMinimumDate(minCal);
        minCal.add(Calendar.DAY_OF_YEAR, 60);
        Calendar maxCal = minCal;
        mCalendarView.setMaximumDate(maxCal);


    }

    //更新UI
    public void onEventMainThread(SendCurrentDateEvent sendCurrentDateEvent) {
        if (sendCurrentDateEvent.getCalendarDay() != null) {
            mCalendarView.setSelectedDate(sendCurrentDateEvent.getCalendarDay());
        }
        EventBus.getDefault().removeStickyEvent(sendCurrentDateEvent);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
