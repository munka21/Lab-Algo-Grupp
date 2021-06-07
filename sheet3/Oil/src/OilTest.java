import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OilTest {
	
	private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    
	private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
	
	@BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
	
	private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    private String getOutput() {
        return testOut.toString();
    }
    
    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }


	@Test
    public void testCaseSampleCase() throws NumberFormatException {
        final String testString = "3\n"
        		+ "-1 4 -1 -2 1 2 -2 -1 -3\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "6\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseBiggerSquare() throws NumberFormatException {
        final String testString = "10\n"
        		+ "-1 4 -1 -2 1 2 -2 -1 -3 5 "
        		+ "1 1 -1 2 -4 -5 5 -2 2 5 "
        		+ "-2 -1 1 1 2 -1 5 3 3 3 "
        		+ "3 1 2 -4 -3 4 -5 -3 -1 -5 "
        		+ "-1 -5 -1 -3 1 3 4 -3 5 3 "
        		+ "-1 2 0 -5 3 -4 2 4 -2 -3 "
        		+ "-5 4 5 4 0 -2 5 0 0 0 "
        		+ "-5 4 0 -1 1 4 -3 -1 2 1 "
        		+ "2 5 0 5 -2 3 -2 1 1 4 "
        		+ "-4 1 2 5 1 3 0 -4 -1 -1 \n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "53\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseEmptyCase() throws NumberFormatException {
        final String testString = "0\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "0\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseNegativeProfit() throws NumberFormatException {
        final String testString = "3\n"
        		+ "-1 -4 -1 -2 -1 -2 -2 -1 -3\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "0\n";      		
        assertEquals(expectedOutput, getOutput());
    }

	@Test
    public void testCaseOnePositivParcel() throws NumberFormatException {
        final String testString = "3\n"
        		+ "-1 -4 -1 -2 -1 2 -2 -1 -3\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	@Test
    public void testCaseOneParcel() throws NumberFormatException {
        final String testString = "1\n"
        		+ "1\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "1\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseAllPositive() throws NumberFormatException {
        final String testString = "3\n"
        		+ "1 4 1 2 1 2 2 1 3\n";
        provideInput(testString);

        Oil.main(new String[0]);
        
        
        final String expectedOutput = "17\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
	void testIndexTransformation() {
		Oil oil=new Oil();
		oil.n=10;
		
		assertEquals(oil.transformIndex(0, 5), 5);
		
		assertEquals(oil.transformIndex(1, 3), 13);

		assertEquals(oil.transformIndex(5, 6), 56);
	}
	
	@Test
	void testRectangleCutting() {
		Oil oil=new Oil();
		oil.n=4;
		oil.parcels=new ArrayList<Integer>();
		oil.allRectanglesSeen=new HashMap<Oil.Rectangle,Oil.Rectangle>();
		for(int i=0; i<16; i++) {
			oil.parcels.add(1);
		}
		Oil.Rectangle rect=oil.new Rectangle(0,0,3,3,16,16);
		ArrayList<Oil.Rectangle> retval= rect.cutOfSlices();
		assertEquals(8, retval.size());

		oil.allRectanglesSeen=new HashMap<Oil.Rectangle,Oil.Rectangle>();
		
		Oil.Rectangle rect2=oil.new Rectangle(0,0,0,1,2,2);
		ArrayList<Oil.Rectangle> retval2= rect2.cutOfSlices();
		assertEquals(2, retval2.size());
	}

}
