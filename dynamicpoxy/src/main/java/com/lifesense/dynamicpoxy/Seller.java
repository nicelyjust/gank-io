package com.lifesense.dynamicpoxy;

/*
 *  @项目名：  gank-io
 *  @包名：    com.lifesense.dynamicpoxy
 *  @文件名:   Seller
 *  @创建者:   lz
 *  @创建时间:  2020/3/18 11:27
 *  @描述：    售票窗口类
 */
public class Seller implements Sell{
    @Override
    public void sellTickets() {
        System.out.println("Seller sell tickets");
    }
}
