package com.eebbk.geek.draftpaper.commandpattern;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.commandpattern
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 9:18
 *  @修改时间:  nicely 2018/8/12 9:18
 *  @描述：    命令接收者类，负责做事的；
 *  用玩俄罗斯方块游戏诠释
 */

public class Receiver {
    public void turnRight(){
        System.out.println("turn right");
    }
    public void turnLeft(){
        System.out.println("turn left");
    }
    public void turnUp(){
        System.out.println("turn up");
    }
    public void turnDown(){
        System.out.println("turn down");
    }
}
