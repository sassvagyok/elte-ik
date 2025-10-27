package text.util;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class CharacterStatisticsTest {
    @Test
    public void emptyText() {
        CharacterStatistics cs = new CharacterStatistics("");

        assertEquals("", cs.toString());
    }

    @Test
    public void aText() {
        CharacterStatistics cs = new CharacterStatistics("aaaaaaaa");

        assertEquals("a(8) ", cs.toString());
    }

    @Test
    public void random1Text() {
        CharacterStatistics cs = new CharacterStatistics("HgFeDcBa");

        assertEquals("a(1) B(1) c(1) D(1) e(1) F(1) g(1) H(1) ", cs.toString());
    }

    @Test
    public void random2Text() {
        CharacterStatistics cs = new CharacterStatistics("a?!_#@{}");

        assertEquals("@(1) a(1) !(1) #(1) {(1) }(1) ?(1) _(1) ", cs.toString());
    }

    @Test
    public void HelloText() {
        CharacterStatistics cs = new CharacterStatistics("Hello world!");

        assertEquals(" (1) !(1) r(1) d(1) e(1) w(1) H(1) l(3) o(2) ", cs.toString());
    }
}
