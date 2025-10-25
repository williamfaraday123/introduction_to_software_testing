/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DemoTest {

    // ----------- Tests for isTriangle() --------------

    @Test
    public void testValidTriangle() {
        // 3, 4, 5 should form a triangle
        assertTrue(Demo.isTriangle(3, 4, 5));
        assertTrue(Demo.isTriangle(4, 5, 3));
        assertTrue(Demo.isTriangle(5, 3, 4));
        assertTrue(Demo.isTriangle(3, 4, 6.5));
    }

    @Test
    public void testInvalidTriangle_SumOfTwoSidesEqualsThird() {
        // 1 + 2 = 3, not a triangle
        assertFalse(Demo.isTriangle(1, 2, 3));
        assertFalse(Demo.isTriangle(2, 3, 5));  // a + b == c
        assertFalse(Demo.isTriangle(3, 5, 2));  // a + c == b
        assertFalse(Demo.isTriangle(5, 9, 4));  // b + c == a
    }

    @Test
    public void testFloatingPointPrecisionCases() {
        assertTrue(Demo.isTriangle(3.0001, 4.0001, 5.0001)); // valid triangle
        assertFalse(Demo.isTriangle(1.5, 2.5, 4.0));         // barely invalid
    }

    @Test
    public void testInvalidTriangle_SumOfTwoSidesLessThanThird() {
        // 2 + 3 < 6, not a triangle
        assertFalse(Demo.isTriangle(2, 3, 6));
    }

    @Test
    public void testValidEquilateralTriangle() {
        // 5, 5, 5 is a valid triangle
        assertTrue(Demo.isTriangle(5, 5, 5));
    }

    @Test
    public void testValidIsoscelesTriangle() {
        // 5, 5, 5 is a valid triangle
        assertTrue(Demo.isTriangle(5, 5, 6));
        assertTrue(Demo.isTriangle(5, 6, 5));
        assertTrue(Demo.isTriangle(6, 5, 5));
    }

    @Test
    public void testInvalidWithNegativeSide() {
        // Negative side lengths not valid
        assertFalse(Demo.isTriangle(-3, 4, 5));
        assertFalse(Demo.isTriangle(-3, -4, 5));
        assertFalse(Demo.isTriangle(-3, -4, -5));
    }

    @Test
    public void testInvalidWithZeroSide() {
        // Zero-length sides not valid
        assertFalse(Demo.isTriangle(0, 4, 5));
    }


    // ----------- Tests for main() -------------------

    @Test
    public void testMain_ValidTriangleInput() {
        // Simulate user input: 3 4 5
        String input = "3\n4\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Demo.main(new String[]{});

        String consoleOutput = out.toString();

        assertTrue("Expected output to mention 'This is a triangle.'",
                   consoleOutput.contains("This is a triangle."));
    }

    @Test
    public void testMain_InvalidTriangleInput() {
        // Simulate user input: 1 2 3
        String input = "1\n2\n3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Demo.main(new String[]{});

        String consoleOutput = out.toString();

        assertTrue("Expected output to mention 'This is not a triangle.'",
                   consoleOutput.contains("This is not a triangle."));
    }

    @Test
    public void testMain_InvalidNegativeSides() {
        // Simulate user input: -3 4 5
        String input = "-3\n4\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Demo.main(new String[]{});

        String consoleOutput = out.toString();

        assertTrue("Expected output to mention 'This is not a triangle.' for negative sides",
                   consoleOutput.contains("This is not a triangle."));
    }
}
