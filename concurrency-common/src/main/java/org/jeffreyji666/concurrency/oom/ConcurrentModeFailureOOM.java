package org.jeffreyji666.concurrency.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiwengang
 * @since 2019/2/23 下午5:37
 */
public class ConcurrentModeFailureOOM {

    public static void main(String[] args) throws Exception {
        List<BigObject> bigObjectList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            bigObjectList.add(new BigObject(1024));
        }
        bigObjectList.add(new BigObject(1024));
        bigObjectList.add(new BigObject(1024));
        System.out.println(bigObjectList);
    }

    static class BigObject {

        private byte[] bigObject;

        BigObject(int size) {
            bigObject = new byte[1024 * size];
        }
    }
}


