package com.eebbk.geek.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eebbk.geek.Book;
import com.eebbk.geek.IBookManager;
import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.service
 *  @文件名:   BasicAndroidActivity
 *  @创建者:   lz
 *  @创建时间:  2019/9/23 18:20
 *  @描述：
 */
public class BasicAndroidActivity extends BaseActivity {
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_bind)
    Button mBtnBind;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.tv_books)
    TextView mTextView;
    private LifeCycleService mService;
    private IBookManager mBookManager;
    private String[] mBookName = new String[]{"深入理解JVM","Android开发艺术探索","Effective Java","图解HTTP"};
    private Random mRandom;

    @Override
    protected int getContentView() {
        return R.layout.activity_basic_android;
    }

    @Override
    protected void initWidget() {
        mRandom = new Random();
    }

    @OnClick({R.id.btn_start, R.id.btn_bind, R.id.btn_stop, R.id.btn_cancel_notification,
            R.id.btn_bind_remote, R.id.btn_unbind_remote, R.id.btn_add, R.id.btn_show, R.id.tv_books})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, LifeCycleService.class);
        switch (view.getId()) {
            case R.id.btn_start:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                } else {
                    startService(intent);
                }
                break;
            case R.id.btn_bind:
                bindService(intent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_stop:
                stopService(intent);
            case R.id.btn_cancel_notification:
                if (mService != null) {
                    int count = mService.getCount();
                    Toast.makeText(this, "count: " + count, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_bind_remote:
                Intent remoteIntent = new Intent(this, RemoteService.class);
                bindService(remoteIntent, mRemoteConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_remote:
                unbindService(mRemoteConnection);
                mBookManager = null;
                break;
            case R.id.btn_add:
                if (mBookManager != null) {
                    int nextInt = mRandom.nextInt(mBookName.length - 1);
                    try {
                        mBookManager.addBook(new Book(nextInt,mBookName[nextInt]));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_show:
                if (mBookManager != null) {
                    try {
                        List<Book> bookList = mBookManager.getBookList();
                        StringBuilder builder = new StringBuilder();
                        for (Book book : bookList) {
                            builder.append(book.toString());
                            builder.append("\n");
                        }
                        mTextView.setText(builder.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        mTextView.setText(e.getMessage());
                    }

                }
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            LifeCycleService.LocalBinder localBinder = (LifeCycleService.LocalBinder) binder;
            mService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection mRemoteConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mBookManager = IBookManager.Stub.asInterface(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBookManager = null;
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, BasicAndroidActivity.class);
        context.startActivity(starter);
    }

}
