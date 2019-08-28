package com.eebbk.nicely.geek.stack;

import androidx.annotation.Nullable;

import org.junit.Test;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.geek.stack
 *  @文件名:   StackOne
 *  @创建者:   lz
 *  @创建时间:  2019/8/28 13:43
 *  @描述：    数组实现堆栈,先进后出,last in first out
 */
public class StackOne {
    @Test
    public void name() {
        Stack<String> stack = new Stack<>(5);
        stack.push("Q");
        stack.push("W");
        stack.push("E");
        stack.push("R");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack.pop());
    }
    class Stack<E> {
        private Object[] elementData;
        // 当前的元素位置
        private int top = -1;
        public Stack(int capacity) {
            elementData = new Object[capacity];
        }
        public void push(E element){
            if (!isFull()){
                top++;
                elementData[top] = element;
            }
        }

        private boolean isFull() {
            return top >= elementData.length-1;
        }

        public @Nullable E pop(){
            E element = null;
            if (!isEmpty()) {
                element = (E) elementData[top];
                elementData[top] = null;
                top--;
            }
            return element;
        }
        public @Nullable E peek(){
            E element = null;
            if (!isEmpty()) {
                element = (E) elementData[top];
            }
            return element;
        }
        private boolean isEmpty() {
            return top == -1;
        }

    }
}
