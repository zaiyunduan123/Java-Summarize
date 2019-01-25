package io.nio;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @Auther: Jesper
 * @Date: 2019/1/23 09:10
 * @Description:
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        //这两个方法都是实例化HeapByteBuffer来创建的ByteBuffer对象
        ByteBuffer allocate = ByteBuffer.allocate(10);
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{});

        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(10);

        System.out.println(allocate);

    }

    public static void clean(final ByteBuffer byteBuffer){
        if (byteBuffer.isDirect()){
            ((DirectBuffer)byteBuffer).cleaner().clean();
        }
    }
}
