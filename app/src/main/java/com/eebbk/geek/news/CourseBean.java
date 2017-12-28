package com.eebbk.geek.news;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.news
 *  @创建者:   lz
 *  @创建时间:  2017/12/28 23:45
 *  @修改时间:  nicely 2017/12/28 23:45
 *  @描述：
 */

import android.support.annotation.NonNull;

public class CourseBean implements Comparable<CourseBean>{
    private String subjectName;
    private int subjectID;
    private String coursePackage;
    private int coursePackageID;

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public CourseBean(String coursePackage, int coursePackageID) {
        this.coursePackage = coursePackage;
        this.coursePackageID = coursePackageID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCoursePackage() {
        return coursePackage;
    }

    public void setCoursePackage(String coursePackage) {
        this.coursePackage = coursePackage;
    }

    public int getCoursePackageID() {
        return coursePackageID;
    }

    public void setCoursePackageID(int coursePackageID) {
        this.coursePackageID = coursePackageID;
    }

    @Override
    public int compareTo(@NonNull CourseBean o) {
        return subjectID - o.getSubjectID();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CourseBean && this.coursePackageID == ((CourseBean) obj).getCoursePackageID();
    }

    @Override
    public int hashCode() {
        return coursePackageID;
    }
}
