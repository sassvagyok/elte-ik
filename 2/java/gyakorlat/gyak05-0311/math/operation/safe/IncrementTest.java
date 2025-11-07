package math.operation.safe;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class IncrementTest {
    @Test
    public void testInc0() {
        assertEquals(1, Increment.increment(0));
    }

    @Test
    public void testIncMin() {
        assertEquals(Integer.MIN_VALUE + 1, Increment.increment(Integer.MIN_VALUE));
    }

    @Test
    public void testIncMax() {
        assertEquals(Integer.MAX_VALUE, Increment.increment(Integer.MAX_VALUE));
    }

    @Test
    public void testInc1000() {
        assertEquals(1001, Increment.increment(1000));
    }

    @Test
    public void testIncMinus1000() {
        assertEquals(-999, Increment.increment(-1000));
    }

    @Test
    public void testIncMinus1() {
        assertEquals(0, Increment.increment(-1));
    }

    // @ParameterizedTest(name = "increment({0}) = {1}")
    // @CsvSource(textBlock = """
    //     0, 1
    //     Integer.MIN_VALUE, Integer.MIN_VALUE,
    //     Integer.MAX_VALUE, Integer.MAX_VALUE,
    //     10000, 10001
    //     -10000, -9999,
    //     -1, 0
    // """)
    // @DisableIfHasBadStructure
    // public void testParameterized(int index, int value) {
    //     assertEquals(value, Increment.increment(index));
    // }
}