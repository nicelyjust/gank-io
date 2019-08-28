package com.eebbk.geek.web;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.eebbk.geek.R;
import com.eebbk.geek.base.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.module.web
 *  @文件名:   WebActivity
 *  @创建者:   lz
 *  @创建时间:  2019/3/14 10:23
 *  @描述：
 */
public class WebActivity extends AppCompatActivity {
    private static final String TAG = "WebActivity";
    public static final String WEB_URL = "WEB_URL";
    @BindView(R.id.pb_web)
    ProgressBar mPb;
    @BindView(R.id.ll_root_view)
    LinearLayout mLlRootView;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initWebView();
        String url = getIntent().getStringExtra(WEB_URL);
        mWebView.loadUrl(TextUtils.isEmpty(url) ? "https://mp.weixin.qq.com/s/xMBcDlebmkpzIZtV8juRvg" : url);
    }

    private void initWebView() {
        mWebView = new WebView(AppManager.getsAppContext());
        mWebView.setBackgroundColor(Color.parseColor("#1B1C1C"));
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (mPb != null) {
                    mPb.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mPb != null) {
                    mPb.setVisibility(View.GONE);
                }
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.w(TAG, "onReceivedError: " + error.getDescription().toString());
            }
        });
        mWebView.setWebChromeClient(mWebChromeClient);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLlRootView.addView(mWebView, params);
        mWebView.setBackgroundColor(0);
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mPb != null) {
                mPb.setProgress(newProgress);
            }
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            //假定传入进来的 url = "js://payMoney?arg1=111&arg2=222"
            Uri uri;
            try {
                uri = Uri.parse(message);
            } catch (Exception e) {
                e.printStackTrace();
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

    };

    public static void start(Context context,String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(WEB_URL ,url);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
                mWebView.destroy();
            }
        }
    }
}
