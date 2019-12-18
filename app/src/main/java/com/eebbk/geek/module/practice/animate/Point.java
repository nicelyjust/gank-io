package com.eebbk.geek.module.practice.animate;

import android.os.Parcel;
import android.os.Parcelable;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn.hencoderpracticedraw1.practice
 *  @文件名:   Point
 *  @创建者:   lz
 *  @创建时间:  2019/10/9 16:28
 *  @描述：    TODO
 */
public class Point implements Parcelable {
    private float x;
    private float y;

    protected Point(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
    }
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(x);
        dest.writeFloat(y);
    }
}
