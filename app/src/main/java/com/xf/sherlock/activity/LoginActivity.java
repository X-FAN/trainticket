package com.xf.sherlock.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
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
import com.xf.sherlock.R;
import com.xf.sherlock.bean.CheckImage;
import com.xf.sherlock.bean.ImagePoint;
import com.xf.sherlock.bean.Login;
import com.xf.sherlock.request.CheckImageService;
import com.xf.sherlock.request.LoginService;
import com.xf.sherlock.utils.CommonUtils;
import com.xf.sherlock.utils.RetrofitUtils;
import com.xf.sherlock.utils.T;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private int mScreenWidth;
    private int mCheckImageHeight;

    private float mGapHeight;
    private float mHeightLimit;

    private static final String CHECK_IMGAE = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";//12306验证码图片
    private List<ImagePoint> recordPoint = new ArrayList<>();
    private List<ImageView> recordImage = new ArrayList<>();
    private CheckImageService mCheckImageService;
    private LoginService mLoginService;

    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView mCheckImage;
    private RelativeLayout mContainer;
    private ViewGroup.LayoutParams mPara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<MotionEvent>() {
                    @Override
                    public void call(MotionEvent motionEvent) {
                        clickLogic(motionEvent);
                    }
                });


    }

    private void loadCheckImage() {
        Picasso.with(this).load(CHECK_IMGAE).skipMemoryCache().resize(mScreenWidth, mCheckImageHeight).into(mCheckImage, new Callback() {
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
    }


    private void initViews() {
        mAccountView = (AutoCompleteTextView) findViewById(R.id.account);
        mPasswordView = (EditText) findViewById(R.id.password);
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

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mCheckImage = (ImageView) findViewById(R.id.check_code_image);
        mContainer = (RelativeLayout) findViewById(R.id.container);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mAccountView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String account = mAccountView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid account address.
        if (TextUtils.isEmpty(account)) {
            mAccountView.setError(getString(R.string.error_field_required));
            focusView = mAccountView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
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

            mCheckImageService.checkImage(randcode.toString(), "sjrand")
                    .subscribeOn(Schedulers.io())
                    .map(new Func1<CheckImage, Observable<Login>>() {
                        @Override
                        public Observable<Login> call(CheckImage checkImage) {
                            if ("1".equals(checkImage.getData().getResult())) {
                                T.showShort(LoginActivity.this, "图片验证正确");
                                login(account, password, randcode);
                            } else {
                                showProgress(false);
                                T.showShort(LoginActivity.this, "图片验证错误");
                            }
                            return null;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Observable<Login>>() {
                        @Override
                        public void call(Observable<Login> loginObservable) {
                            if (loginObservable != null) {
                                loginObservable.subscribe(new Action1<Login>() {
                                    @Override
                                    public void call(Login login) {
                                        if ("Y".equals(login.getData().getLoginCheck())) {
                                            T.showShort(LoginActivity.this, "登录成功");
                                        } else {
                                            T.showShort(LoginActivity.this, login.getMessages().get(0));
                                        }
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                    }
                                });
                            }
                        }
                    });
      /*      Observable<Login> loginOb = mLoginService.login(account.trim(), password, randcode.toString()).subscribeOn(Schedulers.io());

            Observable.concat(checkImageOb, loginOb)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Object>() {

                        @Override
                        public void call(Object o) {
                            if (o instanceof CheckImage) {
                                CheckImage checkImage = (CheckImage) o;
                                if ("1".equals(checkImage.getData().getResult())) {
                                    T.showShort(LoginActivity.this, "图片验证正确");
                                } else {
                                    T.showShort(LoginActivity.this, "图片验证错误");
                                }
                                showProgress(false);
                            } else if (o instanceof Login) {
                                Login login = (Login) o;
                                if ("Y".equals(login.getData().getLoginCheck())) {
                                    T.showShort(LoginActivity.this, "登录成功");
                                } else {
                                    T.showShort(LoginActivity.this, login.getMessages().get(0));
                                }

                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                            showProgress(false);
                        }
                    });

*/
        }
    }

    private Observable<Login> login(String account, String password, StringBuffer randcode) {
        return mLoginService.login(account, password, randcode.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mAccountView.setAdapter(adapter);
    }


}

