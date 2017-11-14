package com.eebbk.geek.bean;


import com.eebbk.geek.view.BaseSpiderBean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean
 *  @文件名:   MockBean
 *  @创建者:   lz
 *  @创建时间:  2017/8/19 17:38
 *  @修改时间:  Administrator 2017/8/19 17:38 
 *  @描述：    TODO
 */
public class MockBean extends BaseSpiderBean {
    private static final String TAG = "MockBean";
    private String  subjectName;
    private int     id;
    private boolean isSelect;

    public MockBean(String subjectName, int id) {
        this.subjectName = subjectName;
        this.id = id;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getSpiderName() {
        return getSubjectName();
    }

    @Override
    public boolean getIsSelectState() {
        return getIsSelect();
    }

    @Override
    public void setSelectState(boolean select) {
        setSelect(select);
    }
}
