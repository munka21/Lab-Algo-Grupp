import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KMeans2Test {

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
    public void testCaseEqualDistance() throws NumberFormatException, IOException {
        final String testString = "2 3 1.00\n"
        		+ "0.00\n"
        		+ "1.00\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "1.00\n"
        		+ "0.00\n"
        		+ "none in range\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseSameCenter() throws NumberFormatException, IOException {
        final String testString = "3 3 1.00\n"
        		+ "0.00\n"
        		+ "12.00\n"
        		+ "0.00\n"
        		+ "1.00\n"
        		+ "0.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "0.00\n"
        		+ "0.00\n"
        		+ "none in range\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseLeftOutside() throws NumberFormatException, IOException {
        final String testString = "2 3 1.00\n"
        		+ "0.00\n"
        		+ "1.00\n"
        		+ "-1.00\n"
        		+ "-1.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "0.00\n"
        		+ "none in range\n"
        		+ "none in range\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	@Test
    public void testCaseNoCenters() throws NumberFormatException, IOException {
        final String testString = "0 3 1.00\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);;
        
        
        final String expectedOutput = "none in range\n"
        		+ "none in range\n"
        		+ "none in range\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseOneCenter() throws NumberFormatException, IOException {
        final String testString = "1 3 1.00\n"
        		+ "-1.00\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "none in range\n"
        		+ "none in range\n"
        		+ "none in range\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	@Test
    public void testCaseOneCenterOnePoint() throws NumberFormatException, IOException {
        final String testString = "1 1 2.00\n"
        		+ "-1.00\n"
        		+ "0.50\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "-1.00\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	
	@Test
    public void testCaseNoQueryPoints() throws NumberFormatException, IOException {
        final String testString = "3 0 1.00\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCasesmallR() throws NumberFormatException, IOException {
        final String testString = "3 10 0.01\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n"
        		+ "4.04\n"
        		+ "0.51\n"
        		+ "-15.00\n"
        		+ "6.70\n"
        		+ "8.99\n"
        		+ "15.00\n"
        		+ "1.10\n"
        		+ "0.49\n"
        		+ "9.99\n"
        		+ "10.00\n";
        provideInput(testString);

        KMeans2V2.mymain(new String[0], testOut);
        
        
        final String expectedOutput = "none in range\n"
        		+ "0.50\n"
        		+ "none in range\n"
        		+ "none in range\n"
        		+ "none in range\n"
        		+ "none in range\n"
        		+ "1.10\n"
        		+ "0.50\n"
        		+ "10.00\n"
        		+ "10.00\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	

}
