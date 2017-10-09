package com.eebbk.nicely.demo.rx;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.media.SelectImgActivity;
import com.eebbk.nicely.demo.media.config.SelectOptions;
import com.eebbk.nicely.demo.utils.L;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.rx
 *  @文件名:   RxJavaActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/25 9:15
 *  @修改时间:  Administrator 2017/9/25 9:15 
 *  @描述：
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener, SelectOptions.Callback {
    private static final String TAG = "RxJavaActivity";
    @BindView(R.id.tv_show)
    TextView mTvShow;

    @Override
    protected int getContentView() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initWidget() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        // 跟160dpi 比例
        L.d(TAG ,"outMetrics.density == " + outMetrics.density);
        L.d(TAG ,"outMetrics.densityDpi == " + outMetrics.densityDpi);
        L.d(TAG ,"outMetrics.heightPixels == " + outMetrics.heightPixels);
        L.d(TAG ,"outMetrics.widthPixels == " + outMetrics.widthPixels);

        /*Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                L.d(TAG, "1");
                e.onNext("Hello,World");
                L.d(TAG, "dispose");
                e.onNext("dispose");
                L.d(TAG, "2");
                e.onNext("Hello,RxJava");
                L.d(TAG, "3");
                e.onComplete();
            }
        });*/
        /*Observable<String> observable = Observable.just("Hello,World","Hello,RxJava");*/
        /*String[] strings = new String[]{"Hello,World","Hello,RxJava"};
        Observable<String> observable = Observable.fromArray(strings);
        observable.subscribe(new Observer<String>() {
//            private Disposable mD;
            @Override
            public void onSubscribe(Disposable d) {
//                mD = d;
                L.d(TAG, "d.isDisposed() == " + d.isDisposed());
            }
            @Override
            public void onNext(String value) {
                L.d(TAG, "value == " + value);
                *//*if (value.equals("dispose")){
                    // TODO:  一旦调用此方法,之后观察者的回调方法都不回调了
                    mD.dispose();
                }*//*
                mTvShow.setText(value);
            }
            @Override
            public void onError(Throwable e) {
                L.d(TAG, e.getMessage());
            }
            @Override
            public void onComplete() {
                L.d(TAG, "onComplete()");
            }
        });*/
        Observable.just("1")
                  .map(new Function<String, Integer>() {
                      @Override
                      public Integer apply(String s)
                              throws Exception
                      {
                          return Integer.parseInt(s);
                      }
                  })
                  .subscribe(new Consumer<Integer>() {
                      @Override
                      public void accept(Integer integer)
                              throws Exception
                      {
                          mTvShow.setText(String.format("%d", integer));
                      }
                  });
        mTvShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SelectOptions options = new SelectOptions.Builder().setCallback(this).setSelectCount(6).build();
        SelectImgActivity.show(this , options);
    }

    @Override
    public void doSelected(String[] images) {
        mTvShow.setText(images[0]);
    }
}
