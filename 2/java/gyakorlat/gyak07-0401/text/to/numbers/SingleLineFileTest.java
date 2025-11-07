package text.to.numbers;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class SingleLineFileTest {
    @Test
    public void testEmpty() {
        try {
            SingleLineFile.addNumbers("test_empty.txt");
        } catch(IllegalArgumentException e) {
            assertEquals("Empty file", e.getMessage());
            return;
        } catch(IOException e) {
            fail();
        }
        fail()
    }

    public void testEmpty() {
        try {
            SingleLineFile.addNumbers("asd.txt");
        } catch(IOException e) {
            return;
        }
        fail()
    }

    @Test
    public void testNormalInput() {
        try {
            int num = SingleLineFile.addNumbers("normalSingleLineFile.txt");

            assertEquals(-117, num);
        } catch(IOException e) {
            fail();
        }

    }
}