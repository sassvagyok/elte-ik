package famous.sequence;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class TriangularNumbersTest {
    @Test
    public void testTri0() {
        assertEquals(0, TriangularNumbers.getTriangularNumber(0));
    }

    @Test
    public void testTri1() {
        assertEquals(1, TriangularNumbers.getTriangularNumber(1));
    }

    @Test
    public void testTriMinus1() {
        assertEquals(0, TriangularNumbers.getTriangularNumber(-1));
    }

    @Test
    public void testTriMinus2() {
        assertEquals(1, TriangularNumbers.getTriangularNumber(-2));
    }

    @Test
    public void testTriWrong() {
        assertEquals(1, TriangularNumbers.getTriangularNumber(10));
    }

    @ParameterizedTest(name = "getTriangulatNumber({0}) = {1}")
    @CsvSource(textBlock = """
        -2, 1
        -1, 0
        0, 0
        1, 1,
        10, 1
    """)
    @DisableIfHasBadStructure
    public void testParameterized(int index, int value) {
        assertEquals(value, TriangularNumbers.getTriangularNumber(index));
    }
}