package com.xf.sherlock.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class QueryResultActivity extends BaseActivity {

    private QueryService mQueryService;
    private QueryCondition mQueryCondition;

    @Bind(R.id.query_result_show)
    RecyclerView mQueryResultShow;
    private QueryResultAdapter mQueryResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        ButterKnife.bind(this);
        initToolBar();
        mQueryCondition = getIntent().getParcelableExtra(MyConstant.QUERY_CONDITION);
        mQueryService = RetrofitUtils.getInstance(this).create(QueryService.class);
        mQueryResultShow.setLayoutManager(new LinearLayoutManager(this));
        mQueryResultShow.setItemAnimator(new DefaultItemAnimator());

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TrainTicketResult>() {
                    @Override
                    public void call(TrainTicketResult trainTicketResult) {
                        List<TicketInfoContainer> ticketInfoContainers = trainTicketResult.getData();
                        if (mQueryResultAdapter == null) {
                            mQueryResultAdapter = new QueryResultAdapter(QueryResultActivity.this, ticketInfoContainers);
                            mQueryResultShow.setAdapter(mQueryResultAdapter);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        T.showShort(QueryResultActivity.this, throwable.getMessage());
                    }
                });


    }

}
