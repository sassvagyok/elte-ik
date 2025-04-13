package data.structure;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import java.util.List;

public class RangedStackTest {
    @Test
    public void testEmpty() {
        RangedStack<Integer> rs1 = new RangedStack<>();
        RangedStack<Integer> rs2 = new RangedStack<>(rs1);

        assertEquals(List.of(), rs1.pop(0));
        assertEquals(List.of(), rs1.pop(1));
        assertEquals(List.of(), rs1.pop(100));
        
        assertEquals(List.of(), rs2.pop(0));
        assertEquals(List.of(), rs2.pop(1));
        assertEquals(List.of(), rs2.pop(100));
    }

   @Test
    public void testInteger() {
        RangedStack<Integer> rs1 = new RangedStack<>();
        rs1.push(2, 4, 6, 8, 10, 12);
        RangedStack<Integer> rs2 = new RangedStack<>(rs1);

        assertEquals(List.of(12), rs1.pop(1));
        assertEquals(List.of(10, 8), rs1.pop(2));
        assertEquals(List.of(6, 4, 2), rs1.pop(3));
        assertEquals(List.of(), rs1.pop(100));

        assertEquals(List.of(12, 10, 8, 6, 4, 2), rs2.pop(100));
        assertEquals(List.of(), rs2.pop(100));
    }

    @Test
    public void testString() {
        RangedStack<String> rs1 = new RangedStack<>();
        rs1.push("a", "b", "c", "d", "e", "f");
        RangedStack<String> rs2 = new RangedStack<>(rs1);

        assertEquals(List.of("f"), rs1.pop(1));
        assertEquals(List.of("e", "d"), rs1.pop(2));
        assertEquals(List.of("c", "b", "a"), rs1.pop(3));
        assertEquals(List.of(), rs1.pop(100));

        assertEquals(List.of("f", "e", "d", "c", "b", "a"), rs2.pop(100));
        assertEquals(List.of(), rs2.pop(100));
    }
}
