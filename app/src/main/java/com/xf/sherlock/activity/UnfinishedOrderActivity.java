package com.xf.sherlock.activity;

import android.os.Bundle;

import com.xf.sherlock.R;

public class UnfinishedOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_order);
        initViews();
    }

    private void initViews() {
        initToolBar();
        setTitle("未完成订单");
    }

}
