package com.xf.sherlock.event;

import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * Created by TC on 2016/1/13.
 */
public class ChooseDateEvent {
    private CalendarDay calendarDay;

    public ChooseDateEvent(CalendarDay calendar) {
        this.calendarDay = calendar;
    }

    public CalendarDay getCalendarDay() {
        return calendarDay;
    }
}
