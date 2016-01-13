package com.xf.sherlock.event;

import com.prolificinteractive.materialcalendarview.CalendarDay;

/**
 * Created by TC on 2016/1/13.
 */
public class SendCurrentDateEvent {
    private CalendarDay calendarDay;

    public CalendarDay getCalendarDay() {
        return calendarDay;
    }

    public SendCurrentDateEvent(CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
    }
}
