package com.xf.sherlock.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.MyConstant;
import com.xf.sherlock.R;
import com.xf.sherlock.adapter.QueryResultAdapter;
import com.xf.sherlock.bean.QueryCondition;
import com.xf.sherlock.bean.TicketInfoContainer;
import com.xf.sherlock.bean.TrainTicketResult;
import com.xf.sherlock.request.QueryService;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class QueryResultActivity extends BaseActivity {

    private QueryService mQueryService;
    private QueryCondition mQueryCondition;


    private QueryResultAdapter mQueryResultAdapter;
    private ProgressDialog mProgressDialog;
    @Bind(R.id.query_result_show)
    RecyclerView mQueryResultShow;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        ButterKnife.bind(this);
        initToolBar();
        setTitle("查询结果");
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在查询...");
        mQueryCondition = getIntent().getParcelableExtra(MyConstant.QUERY_CONDITION);
        mQueryService = RetrofitUtils.getInstance(this).create(QueryService.class);
        mQueryResultShow.setLayoutManager(new LinearLayoutManager(this));
        mQueryResultShow.setItemAnimator(new DefaultItemAnimator());
        RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });
        getData();


    }

    private void getData() {
        mQueryService.getCookie()//获取cookie
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Void, Observable<TrainTicketResult>>() {
                    @Override
                    public Observable<TrainTicketResult> call(Void aVoid) {//查询车票信息
                        String fromStationCode = mQueryCondition.getFromStation().getStationCode();
                        String toStationCode = mQueryCondition.getToStation().getStationCode();
                        String date = mQueryCondition.getDate();
                        return mQueryService.getTrainTicketResult("queryT", date, fromStationCode, toStationCode, "ADULT");
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {//显示进度dialog
                        mProgressDialog.show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrainTicketResult>() {
                    @Override
                    public void call(TrainTicketResult trainTicketResult) {
                        mProgressDialog.cancel();
                        List<TicketInfoContainer> ticketInfoContainers = trainTicketResult.getData();
                        if (mQueryResultAdapter == null) {
                            mQueryResultAdapter = new QueryResultAdapter(QueryResultActivity.this, ticketInfoContainers);
                            mQueryResultShow.setAdapter(mQueryResultAdapter);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mProgressDialog.cancel();
                        T.showShort(QueryResultActivity.this, throwable.getMessage());
                    }
                });
    }


}
