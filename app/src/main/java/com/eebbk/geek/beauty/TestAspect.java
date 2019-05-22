package com.eebbk.geek.beauty;

import com.eebbk.geek.utils.L;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   TestAspect
 *  @创建者:   lz
 *  @创建时间:  2019/5/22 17:56
 *  @描述：切点表达式:execution(<修饰符模式> <返回类型模式> <方法名>(<参数模式>) <异常模式>);
 *         返回类型、方法名、参数是必须的，execution(public * *(..))；
 *         织入一段代码到目标方法前后
 */
@Aspect
public class TestAspect {
    private static final String TAG = "TestAspect";

    @Pointcut("execution(void com.eebbk.geek.beauty.CategoryAdapter.test(..))")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void before(JoinPoint point) {
        L.d(TAG, "@Before: ");
    }
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        L.d(TAG, "@Around");
    }
    @After("pointcut()")
    public void after(JoinPoint point) {
        L.d(TAG,"@After");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point, Object returnValue) {
        L.d(TAG,"@AfterReturning :" + returnValue);
    }
}
