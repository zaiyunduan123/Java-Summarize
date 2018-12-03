package io.nio;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel 存在于 java.nio 包中，属于 NIO 的一种
 */
public class NIODemo {

    public static void main(String[] args){
        try {
            FileChannel fileChannel = new RandomAccessFile(new File("db.data"), "rw").getChannel();
            // 写
            byte[] data = new byte[4096];
            long position = 1024L;
            // 指定 position 写入 4kb 的数据
            fileChannel.write(ByteBuffer.wrap(data), position);

            // 读
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            long position1 = 1024L;
            // 指定 position 读取 4kb 的数据
            fileChannel.read(buffer, position1);
            // 从当前文件指针的位置读取4kb的数据
            fileChannel.read(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
