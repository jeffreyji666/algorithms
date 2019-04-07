package org.jeffreyji666.concurrency.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiwengang
 * @since 2019/2/23 下午5:37
 * -Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps
 */
public class PromotionFailedOOM {

    public static void main(String[] args) throws Exception {
        List<BigObject> bigObjectList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            bigObjectList.add(new BigObject(1024));
        }
        System.out.println(bigObjectList);
    }

    static class BigObject {

        private byte[] bigObject;

        BigObject(int size) {
            bigObject = new byte[1024 * size];
        }
    }
}


