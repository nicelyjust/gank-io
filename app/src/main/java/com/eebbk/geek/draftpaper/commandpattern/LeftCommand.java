package com.eebbk.geek.draftpaper.commandpattern;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.commandpattern
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 9:31
 *  @修改时间:  nicely 2018/8/12 9:31
 *  @描述：    具体命令类
 */

public class LeftCommand implements command {
    private Receiver mReceiver;

    public LeftCommand(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    public void execute() {
        mReceiver.turnLeft();
    }
}
