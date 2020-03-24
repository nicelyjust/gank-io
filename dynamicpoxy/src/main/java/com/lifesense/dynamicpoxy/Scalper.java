package com.lifesense.dynamicpoxy;

/*
 *  @项目名：  gank-io
 *  @包名：    com.lifesense.dynamicpoxy
 *  @文件名:   Scalper
 *  @创建者:   lz
 *  @创建时间:  2020/3/18 11:28
 *  @描述：    黄牛就是代理类,售票窗口是被代理类
 */
public class Scalper implements Sell {
    private Sell mSell;

    public Scalper(Sell sell) {
        mSell = sell;
    }

    @Override
    public void sellTickets() {
        System.out.println("Scalper start");
        mSell.sellTickets();
        System.out.println("Scalper end");
    }
}
