package com.eebbk.geek.draftpaper.tuya;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 12:48
 *  @修改时间:  nicely 2018/8/12 12:48
 *  @描述：    涂鸦控件;具体命令接收者
 */

public class DrawCanvas extends SurfaceView implements SurfaceHolder.Callback {
    //标记是否可以绘制,绘制线程是否正在跑
    public boolean isDrawing, isRunnig;
    private DrawInvoker mDrawInvoker;
    private Bitmap mBitmap;
    private DrawThread mDrawThread;
    private DrawPath mDrawPath;
    private Paint mPaint;
    private IBrush mBrush;

    public DrawCanvas(Context context) {
        this(context , null);
    }

    public DrawCanvas(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public DrawCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawInvoker = new DrawInvoker();
        mDrawThread = new DrawThread();
        getHolder().addCallback(this);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);

        mBrush = new NormalBrush();
    }

    public void add(DrawPath path) {

        mDrawInvoker.add(path);
    }

    public void undo(){
        isDrawing = true;
        mDrawInvoker.undo();
    }
    public boolean isCanUndo(){
        return mDrawInvoker.isCanUndo();
    }

    public void redo(){
        isDrawing = true;
        mDrawInvoker.redo();
    }
    public boolean isCanRedo(){
        return mDrawInvoker.isCanRedo();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunnig = true;
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        isRunnig = false;
        while (retry){
            try {
                mDrawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDrawPath = new DrawPath();
                mDrawPath.mPaint = mPaint;
                mDrawPath.mPath = new Path();
                mBrush.down(mDrawPath.mPath , event.getX() , event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mBrush.move(mDrawPath.mPath , event.getX() , event.getY());
                break;
            case MotionEvent.ACTION_UP:
                mBrush.up(mDrawPath.mPath , event.getX() , event.getY());
                add(mDrawPath);
                isDrawing = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        return true;
    }

    private class DrawThread extends Thread{
        @Override
        public void run() {
            Canvas canvas = null;
            while (isRunnig){

                try {
                    canvas = getHolder().lockCanvas(null);
                    if (mBitmap == null) {
                        mBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                    }
                    Canvas c = new Canvas(mBitmap);
                    c.drawColor(0 , PorterDuff.Mode.CLEAR);

                    canvas.drawColor(0 , PorterDuff.Mode.CLEAR);
                    mDrawInvoker.execute(canvas);
                } finally {
                    getHolder().unlockCanvasAndPost(canvas);
                }
                isDrawing = false;
            }
        }
    }
}
