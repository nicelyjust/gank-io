package com.eebbk.geek.draftpaper.commandpattern;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.commandpattern
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 10:01
 *  @修改时间:  nicely 2018/8/12 10:01
 *  @描述：    请求者类,调用命令对象,执行命令,也可记录命令
 */

public class Invoker {
    private LeftCommand mLeftCommand;
    private RightCommand mRightCommand;
    private TransformCommand mTransformCommand;
    private DownCommand mDownCommand;

    public void setLeftCommand(LeftCommand leftCommand) {
        mLeftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        mRightCommand = rightCommand;
    }

    public void setTransformCommand(TransformCommand transformCommand) {
        mTransformCommand = transformCommand;
    }

    public void setDownCommand(DownCommand downCommand) {
        mDownCommand = downCommand;
    }

    public void toLeft(){
        if (mLeftCommand != null) {
            mLeftCommand.execute();
        }
    }
    public void toRight(){
        if (mRightCommand != null) {
            mRightCommand.execute();
        }
    }
    public void toDown(){
        if (mDownCommand != null) {
            mDownCommand.execute();
        }
    }
    public void toTransform(){
        if (mTransformCommand != null) {
            mTransformCommand.execute();
        }
    }
}
