package org.jeffreyji666.concurrency.oom;

import java.util.concurrent.TimeUnit;

//-Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
public class ConcurrentModeOOMTest {
    public static void main(String[] args) throws Exception {
        BigObj o1 = new BigObj(1024 * 1024 * 2);
        BigObj o2 = new BigObj(1024 * 1024 * 2);
        BigObj o3 = new BigObj(1024 * 1024 * 2);
        BigObj o4 = new BigObj(1024 * 1024 * 2);
        TimeUnit.SECONDS.sleep(2);

        BigObj o5 = new BigObj(1024 * 1024 * 2);
        BigObj o6 = new BigObj(1024 * 1024 * 2);
        BigObj o7 = new BigObj(1024 * 1024 * 2);
        BigObj o8 = new BigObj(1024 * 1024 * 2);
        TimeUnit.SECONDS.sleep(2);
    }

    private static class BigObj {

        private byte[] b;

        public BigObj(int c) {
            b = new byte[c];
        }
    }
}
