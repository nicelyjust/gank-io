package com.eebbk.nicely.geek.array;

import androidx.annotation.IntRange;

import org.junit.Test;

import java.util.Arrays;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.geek.array
 *  @文件名:   Array_1
 *  @创建者:   lz
 *  @创建时间:  2019/8/27 14:05
 *  @描述：    如何实现动态扩容的数组
 */
public class Array_1 {
    @Test
    public void addition_isCorrect() throws Exception {
        GenericArray<String> array = new GenericArray<>(3);
        array.add("Q");
        System.out.println(array.toString());
        array.add("W");
        System.out.println(array.toString());
        array.add("E");
        System.out.println(array.toString());
        array.add("R");
        System.out.println(array.toString());
        array.add("D");
        System.out.println(array.toString());
        array.add("F");
        System.out.println(array.toString());
    }

    public class GenericArray<E>{
        private Object[] data;
        private int size;

        public GenericArray(@IntRange(from = 1) int capacity) {
            this.data = new Object[capacity];
        }

        public void add(E e){
            ensureCapacity(size + 1);
            data[size++] = e;
        }
        public void add(int index ,E e){
            if (index > size || index < 0)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            ensureCapacity(size + 1);
            System.arraycopy(data,index,data,index+1,size - index);
            data[index] = e;
            size++;
        }
        private void ensureCapacity(int minCapacity) {
            if (minCapacity > data.length) {
                grow(minCapacity);
            }
        }

        private void grow(int minCapacity) {
            int oldCapacity = data.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            System.out.println("gow size == " + newCapacity);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            if (newCapacity > Integer.MAX_VALUE -8){
                throw new OutOfMemoryError();
            }
            data = Arrays.copyOf(data, newCapacity);
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+size;
        }

        @Override
        public String toString() {
            return "GenericArray{" +
                    "data=" + Arrays.toString(data) +
                    ", size=" + size +
                    '}';
        }
    }
}
