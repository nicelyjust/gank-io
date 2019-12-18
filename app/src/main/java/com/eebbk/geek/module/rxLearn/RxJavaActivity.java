package com.eebbk.geek.module.rxLearn;

import androidx.core.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;
import com.eebbk.geek.media.config.SelectOptions;
import com.eebbk.geek.module.rxLearn.model.MobileAddress;
import com.eebbk.geek.utils.L;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 *  @项目名：  Demo
 *  @包名：    com.eebbk.nicely.demo.rx
 *  @文件名:   RxJavaActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/25 9:15
 *  @修改时间:  Administrator 2017/9/25 9:15
 *  @描述：
 */
public class RxJavaActivity extends BaseActivity
        implements View.OnClickListener, SelectOptions.Callback {
    private static final String TAG = "RxJavaActivity";
    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.sv)
    NestedScrollView mSv;
    private Disposable mDisposable;

    @Override
    protected int getContentView() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initWidget() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        // 跟160dpi 比例
        L.d(TAG, "outMetrics.density == " + outMetrics.density);
        L.d(TAG, "outMetrics.densityDpi == " + outMetrics.densityDpi);
        L.d(TAG, "outMetrics.heightPixels == " + outMetrics.heightPixels);
        L.d(TAG, "outMetrics.widthPixels == " + outMetrics.widthPixels);
    }

    @Override
    public void onClick(View v) {
        /*SelectOptions options = new SelectOptions.Builder().setCallback(this).setSelectCount(6).build();
        SelectImgActivity.show(this , options);*/
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @Override
    public void doSelected(String[] images) {
        mTvShow.setText(images[0]);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void onViewClicked(View view) {
        mTvShow.setText("");
        switch (view.getId()) {
            case R.id.btn_1:
                mDisposable = Observable.create(new ObservableOnSubscribe<Response>() {
                    @Override
                    public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                        L.d(TAG, "create: ");
                        Request.Builder builder = new Request.Builder()
                                .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                                .get();
                        Request request = builder.build();
                        Call call = new OkHttpClient().newCall(request);
                        emitter.onNext(call.execute());
                    }
                }).subscribeOn(Schedulers.io())
                        .map(new Function<Response, MobileAddress>() {
                            @Override
                            public MobileAddress apply(Response response) throws Exception {
                                L.d(TAG, "map: ");
                                return new Gson().fromJson(response.body().string(), MobileAddress.class);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<MobileAddress>() {
                            @Override
                            public void accept(MobileAddress mobileAddress) throws Exception {
                                L.d(TAG, "doOnNext: ");
                                mTvShow.append(getString(R.string.rx_thread, "doOnNext: " + mobileAddress.toString(), Thread.currentThread().getName()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<MobileAddress>() {
                            @Override
                            public void accept(MobileAddress mobileAddress) throws Exception {
                                L.d(TAG, "accept: ");
                                mTvShow.append(getString(R.string.rx_thread, "subscribe: " + mobileAddress.toString(), Thread.currentThread().getName()));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mTvShow.append(getString(R.string.rx_thread, "subscribe: error-->" + throwable.getMessage(), Thread.currentThread().getName()));
                            }
                        });
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
