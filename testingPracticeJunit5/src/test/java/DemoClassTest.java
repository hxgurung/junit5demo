import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoClassTest extends Object {

    private DemoClass tester;

    @BeforeEach
    void setup(){
        tester = new DemoClass();
    }

    @Test
    public void testGrouped(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> tester.multiply(2000,2)),
                ()-> assertEquals(50, tester.multiply( 10, 5)," 10 x 5 must be 50")
        );
    }
    @Disabled
    @Test
    void testExceptionIsThrow() {
        assertThrows(IllegalArgumentException.class,() -> tester.multiply(1000,2));
            }
    @Disabled
    @Test
    void testMultiply(){
        assertEquals(50, tester.multiply(10,5), "10 x 5 must be 50");
    }
}