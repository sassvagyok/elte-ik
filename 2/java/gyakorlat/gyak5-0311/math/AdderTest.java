package math;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class AdderTest {
    @Test
    public void testAddEmpty() {
        assertEquals(0, Adder.add(new int[]{}));
    }

    @Test
    public void testAdd1() {
        assertEquals(10, Adder.add(new int[]{10}));
    }

    @Test
    public void testAdd2() {
        assertEquals(125, Adder.add(new int[]{5, 120}));
    }

    @Test
    public void testAdd5() {
        assertEquals(57, Adder.add(new int[]{10, 20, 11, 16, 0}));
    }

    @Test
    public void testAddNegative() {
        assertEquals(-57, Adder.add(new int[]{-10, -20, -11, -16, 0}));
    }

    @Test
    public void testAddNegativeAndPositive() {
        assertEquals(15, Adder.add(new int[]{-10, 20, -11, 16, 0}));
    }
}