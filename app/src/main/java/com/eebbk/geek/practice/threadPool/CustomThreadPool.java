package com.eebbk.geek.practice.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.threadPool
 *  @文件名:   CustomThreadPool
 *  @创建者:   lz
 *  @创建时间:  2019/9/29 16:09
 *  @描述：
 */
public class CustomThreadPool {

    private final ExecutorService mService;

    private static class SingletonHolder {
        private static CustomThreadPool INSTANCE = new CustomThreadPool();
    }

    private CustomThreadPool() {
        // corePoolSize:核心线程数,即使idle也不会销毁,除非设置了allowCoreThreadTimeOut = true
        // maximumPoolSize:容纳的最大线程数,后续新任务将会阻塞排队,包含核心线程+非核心线程
        // keepAliveTime: 非核心线程超时时间,设置了allowCoreThreadTimeOut = true,即核心线程超时时间
        mService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    public static CustomThreadPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void execute(Runnable command) {
        mService.execute(command);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return mService.submit(task);
    }
}
