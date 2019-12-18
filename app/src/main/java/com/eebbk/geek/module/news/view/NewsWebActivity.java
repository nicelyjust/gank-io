package com.eebbk.geek.module.news.view;

import androidx.appcompat.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import butterknife.BindView;

public class NewsWebActivity extends BaseActivity {

    @BindView(R.id.toolbar_news_web)
    Toolbar mToolbar;
    @BindView(R.id.pb_news_web)
    ProgressBar mPb;
    @BindView(R.id.webView_news_web)
    WebView mWebView;

    @Override
    protected int getContentView() {
        return R.layout.activity_news_web;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }
}
