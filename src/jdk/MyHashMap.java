package jdk;

import java.util.HashMap;

/**
 * Created by jiangyunxiong on 2018/4/28.
 */
public class MyHashMap extends HashMap {
    @Override
    public Object put(Object key, Object value) {
        return super.put(key, value);
    }

    static int indexOf(int hash, int length){
        return hash & (length - 1);
    }

    Entry[] table = null;


}
