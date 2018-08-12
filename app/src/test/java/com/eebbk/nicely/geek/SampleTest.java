package com.eebbk.nicely.geek;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.geek
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 9:52
 *  @修改时间:  nicely 2018/8/12 9:52
 *  @描述：
 */

import com.eebbk.geek.draftpaper.commandpattern.DownCommand;
import com.eebbk.geek.draftpaper.commandpattern.Invoker;
import com.eebbk.geek.draftpaper.commandpattern.LeftCommand;
import com.eebbk.geek.draftpaper.commandpattern.Receiver;
import com.eebbk.geek.draftpaper.commandpattern.RightCommand;
import com.eebbk.geek.draftpaper.commandpattern.TransformCommand;

import org.junit.Test;

public class SampleTest {
    /**
     * 一般代码: 直接new Receiver(),toLeft,toRight...
     * GUI 开发中比较常用
     * @throws Exception
     */
    @Test
    public void addition_isCorrect()
            throws Exception {
        // 首先得有具体命令接收者执行者
        Receiver receiver = new Receiver();

        LeftCommand leftCommand = new LeftCommand(receiver);
        RightCommand rightCommand = new RightCommand(receiver);
        TransformCommand transformCommand = new TransformCommand(receiver);
        DownCommand downCommand = new DownCommand(receiver);
        // 请求者角色
        Invoker invoker = new Invoker();
        invoker.setRightCommand(rightCommand);
        invoker.setTransformCommand(transformCommand);
        invoker.setLeftCommand(leftCommand);
        invoker.setDownCommand(downCommand);

        //
        invoker.toLeft();
        invoker.toTransform();
        invoker.toRight();
        invoker.toDown();

    }
}
