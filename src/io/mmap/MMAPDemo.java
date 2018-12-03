package io.mmap;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MMAP是由 FileChannel 调用 map 方法衍生出来的一种特殊读写文件的方式，被称之为内存映射。
 */
public class MMAPDemo {

    public static void main(String[] args) {
        try {
            FileChannel fileChannel = new RandomAccessFile(new File("db.data"), "rw").getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
