package org.jeffreyji666.algorithms.backtracking;

import org.jeffreyji.algorithms.backtrackint.FixedSumOfSubArray;
import org.junit.Test;

/**
 * @author: wgji
 * @date：2014年3月27日 下午6:12:04
 * @comment:
 */

public class FixedSumOfSubArrayTest {
    int[] x = { 3, 2, 4, 1, 6 };

    @Test
    public void testFixedSumOfSubArray() {
        FixedSumOfSubArray.getCombination(x, 0, 7);
    }
}
