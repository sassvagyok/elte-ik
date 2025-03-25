package array.util;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class ArrayUtilTest {
    @Test
    public void maxLength0() {
        assertEquals(0, ArrayUtil.max(new int[]{}));
        assertEquals(0, ArrayUtil.max2(new int[]{}));
        assertEquals(0, ArrayUtil.max3(new int[]{}));
        assertEquals(0, ArrayUtil.max4(new int[]{}));
    }

    @ParameterizedTest(name = "maxLength1([{0}]) = {0}")
    @CsvSource(textBlock = """
        0
        1
        10000
        -10000
    """)
    @DisableIfHasBadStructure
    public void maxLength1(int value) {
        int[] t = new int[1];
        t[0] = value;

        assertEquals(value, ArrayUtil.max(t));
    }
}