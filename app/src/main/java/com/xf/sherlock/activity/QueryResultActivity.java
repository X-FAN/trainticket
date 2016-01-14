package com.xf.sherlock.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.adapter.QueryResultAdapter;
import com.xf.sherlock.bean.QueryCondition;
import com.xf.sherlock.bean.TicketInfoContainer;
import com.xf.sherlock.bean.TrainTicketResult;
import com.xf.sherlock.event.SendQueryConditionEvent;
import com.xf.sherlock.request.QueryService;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class QueryResultActivity extends BaseActivity {

    private QueryService mQueryService;
    private QueryCondition mQueryCondition;


    private QueryResultAdapter mQueryResultAdapter;
    private Handler myHandler = new Handler();
    @Bind(R.id.query_result_show)
    RecyclerView mQueryResultShow;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.pre)
    TextView mPre;
    @Bind(R.id.next)
    TextView mNext;
    @Bind(R.id.date)
    TextView mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        initViews();
        addListener();
        EventBus.getDefault().registerSticky(this);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initViews() {
        ButterKnife.bind(this);
        initToolBar();
        setTitle("查询结果");
        mQueryService = RetrofitUtils.getInstance(this).create(QueryService.class);
        mQueryResultShow.setLayoutManager(new LinearLayoutManager(this));
        mQueryResultShow.setItemAnimator(new DefaultItemAnimator());
        mQueryResultAdapter = new QueryResultAdapter(QueryResultActivity.this, new ArrayList<TicketInfoContainer>());
        mQueryResultShow.setAdapter(mQueryResultAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
    }

    public void onEventMainThread(SendQueryConditionEvent sendQueryConditionEvent) {
        mQueryCondition = sendQueryConditionEvent.getQueryCondition();
        mDate.setText(CommonUtils.getDate(mQueryCondition.getDate()));
        initData();
        EventBus.getDefault().removeStickyEvent(sendQueryConditionEvent);
    }

    private void initData() {
        getWindow().getDecorView().post(new Runnable() {//首次加载
            @Override
            public void run() {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        getData();
                    }
                });
            }
        });
    }

    private void addListener() {
        RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout)
                .compose(QueryResultActivity.this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getData();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        T.showShort(QueryResultActivity.this, throwable.getMessage());
                    }
                });
        if (!mSwipeRefreshLayout.isRefreshing()) {
            RxView.clicks(mPre).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    Calendar cal = mQueryCondition.getDate();
                    cal.add(Calendar.DAY_OF_YEAR, -1);
                    mDate.setText(CommonUtils.getDate(cal));
                    mSwipeRefreshLayout.setRefreshing(true);
                    getData();
                }
            });
        }

        if (!mSwipeRefreshLayout.isRefreshing()) {
            RxView.clicks(mNext).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    Calendar cal = mQueryCondition.getDate();
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    mSwipeRefreshLayout.setRefreshing(true);
                    mDate.setText(CommonUtils.getDate(cal));
                    getData();
                }
            });
        }

    }


    private void getData() {
        mQueryService.getCookie()//获取cookie
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Void, Observable<TrainTicketResult>>() {
                    @Override
                    public Observable<TrainTicketResult> call(Void aVoid) {//查询车票信息
                        String fromStationCode = mQueryCondition.getFromStation().getStationCode();
                        String toStationCode = mQueryCondition.getToStation().getStationCode();
                        String date = CommonUtils.getDate(mQueryCondition.getDate());
                        return mQueryService.getTrainTicketResult("queryT", date, fromStationCode, toStationCode, "ADULT");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrainTicketResult>() {
                    @Override
                    public void call(TrainTicketResult trainTicketResult) {//更新界面
                        mSwipeRefreshLayout.setRefreshing(false);
                        List<TicketInfoContainer> ticketInfoContainers = trainTicketResult.getData();
                        if (mQueryResultAdapter == null) {
                            mQueryResultAdapter = new QueryResultAdapter(QueryResultActivity.this, ticketInfoContainers);
                            mQueryResultShow.setAdapter(mQueryResultAdapter);
                        } else {
                            mQueryResultAdapter.setData(ticketInfoContainers);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        T.showShort(QueryResultActivity.this, throwable.getMessage());
                    }
                });
    }


}
