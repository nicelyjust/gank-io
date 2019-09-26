package com.eebbk.geek.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.eebbk.geek.Book;
import com.eebbk.geek.IBookManager;
import com.eebbk.geek.R;
import com.eebbk.geek.base.AppManager;
import com.eebbk.geek.utils.L;

import java.util.ArrayList;
import java.util.List;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.service
 *  @文件名:   LifeCycleService
 *  @创建者:   lz
 *  @创建时间:  2019/9/23 18:03
 *  @描述：    两种启动方式:1.startService,需要手动stop,第一次start,周期start()-->startCommand(),随后start只走startCommand();
 * 2.bind方式,绑定当前组件的生命周期,无需手动解绑,只能绑定一次;
 * 3.混合启动:stop与
 */
public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    private List<Book> mBookList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        L.d(TAG, "onCreate: ");
        NotificationManager notificationManager = (NotificationManager) AppManager.getsAppContext().getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("ID","NAME",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this,"ID")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            notification.flags = Notification.FLAG_ONGOING_EVENT;
            startForeground(2,notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    public Binder getBinder() {
        return mBinder;
    }

    public Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


}
