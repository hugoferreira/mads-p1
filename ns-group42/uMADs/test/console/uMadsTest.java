package tests.console;

import console.uMads;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author ei10139
 */
public class uMadsTest {
    
    public uMadsTest() {
    }
    
    @Test
    public void testAnalyze() {
        System.out.println("testing analyze");
        uMads instance = new uMads();
        instance.analyze('X');
    }
}
