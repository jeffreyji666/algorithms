package org.jeffreyji666.algorithms.sort;

import org.jeffreyji.algorithms.sort.QuickSort;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: wgji
 * @date：2014年3月27日 下午5:50:16
 * @comment:
 */

public class QuickSortTest {
    int[] x = { 3, 2, 4, 1, 6 };
    int[] y = { 3, 2, 4, 1, 6 };

    @Test
    public void testQuickSort() {
        QuickSort.quickSort1(x, 0, x.length - 1);
        System.out.println("*********************");
        QuickSort.quickSort2(y, 0, y.length - 1);
        Assert.assertArrayEquals(x, y);
    }
}
