package com.lifesense.dynamicpoxy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * 静态代理
     */
    @Test
    public void testStaticProxy(){
        //目标对象
        Seller seller = new Seller();
        //
        Scalper scalper = new Scalper(seller);
        scalper.sellTickets();

    }
    /**
     * 动态代理
     */
    @Test
    public void testDynamicProxy(){
        //目标对象
        Seller seller = new Seller();
        //
        DynamicProxy dynamicProxy = new DynamicProxy();
        Sell sell = (Sell) dynamicProxy.getProxyInstance(seller);
        sell.sellTickets();
    }
}