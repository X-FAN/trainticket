package com.xf.sherlock.activity;

import android.os.Bundle;

import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.bean.UnfinishedOrder;
import com.xf.sherlock.request.QueryService;
import com.xf.sherlock.utils.RetrofitUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UnfinishedOrderActivity extends BaseActivity {

    private QueryService mQueryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_order);
        initViews();
        mQueryService = RetrofitUtils.getInstance(this).create(QueryService.class);
        mQueryService.queryUnfinishedOrder()
                .compose(this.<UnfinishedOrder>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UnfinishedOrder>() {
                    @Override
                    public void call(UnfinishedOrder unfinishedOrder) {
                          unfinishedOrder.getUnfinishedData();
                    }
                });
    }

    private void initViews() {
        initToolBar();
        setTitle("未完成订单");
    }

}
