package com.xf.sherlock.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.ActivityEvent;
import com.xf.sherlock.R;
import com.xf.sherlock.bean.CheckImage;
import com.xf.sherlock.bean.ImagePoint;
import com.xf.sherlock.bean.Login;
import com.xf.sherlock.request.CheckImageService;
import com.xf.sherlock.request.LoginService;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.L;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private int mScreenWidth;
    private int mCheckImageHeight;

    private float mGapHeight;
    private float mHeightLimit;

    private static final String CHECK_IMAGE = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";//12306验证码图片
    private List<ImagePoint> recordPoint = new ArrayList<>();
    private List<ImageView> recordImage = new ArrayList<>();
    private CheckImageService mCheckImageService;
    private LoginService mLoginService;

    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;
    private ImageView mCheckImage;
    private RelativeLayout mContainer;
    private ViewGroup.LayoutParams mPara;
    private TextView mRefresh;//刷新
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
        initParams();
        getCheckImage();
        mCheckImageService = RetrofitUtils.getInstance(this).create(CheckImageService.class);
        mLoginService = RetrofitUtils.getInstance(this).create(LoginService.class);
    }

    private void getCheckImage() {
        loadCheckImage();
        RxView.touches(mCheckImage, new Func1<MotionEvent, Boolean>() {
            @Override
            public Boolean call(MotionEvent motionEvent) {
                return true;
            }
        })
                .compose(this.<MotionEvent>bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(500, TimeUnit.MILLISECONDS)

                .subscribe(new Action1<MotionEvent>() {
                    @Override
                    public void call(MotionEvent motionEvent) {
                        clickLogic(motionEvent);
                    }
                });


    }

    private void loadCheckImage() {
        Picasso.with(this).load(CHECK_IMAGE).skipMemoryCache().resize(mScreenWidth, mCheckImageHeight).into(mCheckImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Toast.makeText(LoginActivity.this, "获取验证图片失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    //点击验证图片的逻辑处理
    private void clickLogic(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = motionEvent.getX();
                float y = motionEvent.getY() - mGapHeight;
                if (y < 0) {//超出验证图片大小范围内不做任何响应
                    break;
                } else if (y > mHeightLimit) {
                    break;
                }
                ImagePoint point = new ImagePoint((int) (x * 293f / mPara.width), (int) (y * 190f / mPara.height));
                final ImageView iv = new ImageView(LoginActivity.this);
                iv.setImageResource(R.drawable.selected);
                iv.setTag(point);
                RxView.clicks(iv).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        iv.setVisibility(View.GONE);
                        recordPoint.remove(iv.getTag());
                        recordImage.remove(iv);
                    }
                });
                recordImage.add(iv);
                recordPoint.add(point);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(50, 50);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp.topMargin = (int) (y + mGapHeight - 25);
                lp.leftMargin = (int) (x - 25);
                iv.setLayoutParams(lp);
                mContainer.addView(iv);
                break;
            default:
                break;
        }
    }

    //初始化参数
    private void initParams() {
        int padding = CommonUtils.dp2px(this, 16);
        int gap = CommonUtils.dp2px(this, 30);
        mScreenWidth = CommonUtils.getWidth(this);
        mCheckImageHeight = (mScreenWidth - padding) * 190 / 293;
        mPara = mCheckImage.getLayoutParams();
        mPara.width = mScreenWidth - gap;
        mPara.height = 190 * mPara.width / 293;
        mGapHeight = mPara.height * 3f / 19f;
        mHeightLimit = mPara.height - mGapHeight - 25;
        initRefreshPosition();
    }

    //设置刷新按钮的位置
    private void initRefreshPosition() {

        float textGap = (mGapHeight - mRefresh.getTextSize()) / 2.0f;
        int padding = Math.round(textGap);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mRefresh.getLayoutParams();
        lp.topMargin = padding;
        mRefresh.setLayoutParams(lp);
    }


    private void initViews() {
        initToolBar();
        setTitle("登录12306");
        mAccountView = (AutoCompleteTextView) findViewById(R.id.account);
        mPasswordView = (EditText) findViewById(R.id.password);
        mCheckImage = (ImageView) findViewById(R.id.check_code_image);
        mContainer = (RelativeLayout) findViewById(R.id.container);
        mRefresh = (TextView) findViewById(R.id.refresh);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在登陆");

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        RxView.clicks(signInButton)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        attemptLogin();
                    }
                });
        RxView.clicks(mRefresh)
                .compose(this.<Void>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        clearImage();
                        loadCheckImage();
                    }
                });
    }


    private void attemptLogin() {

        mAccountView.setError(null);
        mPasswordView.setError(null);

        final String account = mAccountView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(account)) {
            mAccountView.setError(getString(R.string.error_field_required));
            focusView = mAccountView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            final StringBuffer randcode = getRandCode();
            mCheckImageService.checkImage(randcode.toString(), "sjrand")
                    .compose(this.<CheckImage>bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .flatMap(new Func1<CheckImage, Observable<Login>>() {//验证图片
                        @Override
                        public Observable<Login> call(CheckImage checkImage) {
                            return getLoginObservable(checkImage, account, password, randcode);

                        }
                    })
                    .doOnSubscribe(new Action0() {//显示进度条
                        @Override
                        public void call() {
                            mProgressDialog.show();
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Login>() {//检验登录结果
                        @Override
                        public void call(Login login) {
                            mProgressDialog.dismiss();
                            if ("Y".equals(login.getData().getLoginCheck())) {
                                T.showShort(LoginActivity.this, "登录成功");
                                finish();
                            } else {
                                T.showShort(LoginActivity.this, login.getMessages().get(0));
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            L.e(throwable.getMessage());
                            mProgressDialog.dismiss();
                            T.showShort(LoginActivity.this, throwable.getMessage());
                        }
                    });


        }
    }

    @Nullable
    private Observable<Login> getLoginObservable(CheckImage checkImage, String account, String password, StringBuffer randcode) {
        if ("1".equals(checkImage.getData().getResult())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    T.showShort(LoginActivity.this, "图片验证正确");
                }
            });
            return getLoginOb(account, password, randcode);//登录
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                    T.showShort(LoginActivity.this, "图片验证错误");
                }
            });
            return null;
        }
    }

    @NonNull
    private StringBuffer getRandCode() {
        final StringBuffer randcode = new StringBuffer();
        Observable.from(recordPoint)
                .subscribe(new Observer<ImagePoint>() {
                    @Override
                    public void onCompleted() {
                        randcode.deleteCharAt(randcode.length() - 1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ImagePoint imagePoint) {
                        randcode.append(imagePoint.getX()).append(",").append(imagePoint.getY()).append(",");
                    }
                });
        return randcode;
    }


    private Observable<Login> getLoginOb(String account, String password, StringBuffer randcode) {
        return mLoginService.login(account, password, randcode.toString())
                .subscribeOn(Schedulers.io());

    }

    //清除验证图片上的点击图标
    private void clearImage() {
        for (ImageView iv : recordImage) {
            iv.setVisibility(View.GONE);
        }
    }


}

