package com.xf.sherlock.event;

import com.xf.sherlock.bean.QueryCondition;

/**
 * Created by TC on 2016/1/14.
 */
public class SendQueryConditionEvent {
    private QueryCondition queryCondition;

    public SendQueryConditionEvent(QueryCondition queryCondition) {
        this.queryCondition = queryCondition;
    }

    public QueryCondition getQueryCondition() {
        return queryCondition;
    }
}
