import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KMeans3Test {

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
    public void testCaseSampleCase() throws NumberFormatException, IOException {
        final String testString = "2 1.000\n"
        		+ "0.000 0.000\n"
        		+ "0.000 0.000\n"
        		+ "1.000 1.000\n"
        		+ "0.500 0.500\n"
        		+ "2.000 1.000\n"
        		+ "2.500 0.500\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "3\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	@Test
    public void testCaseEquidistantePointsOnCircle() throws NumberFormatException, IOException {
        final String testString = "2 1.415\n"
        		+ "1.000 0.000\n"
        		+ "0.000 1.000\n"
        		+ "0.000 -1.000\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "1\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseEquidistantePointsOnLine() throws NumberFormatException, IOException {
        final String testString = "2 1.000\n"
        		+ "1.000 0.000\n"
        		+ "2.000 0.000\n"
        		+ "3.000 0.000\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseEquidistantePointsOnLine2() throws NumberFormatException, IOException {
        final String testString = "2 1.000\n"
        		+ "2.000 0.000\n"
        		+ "1.000 0.000\n"
        		+ "3.000 0.000\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "1\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseMoreDimensions() throws NumberFormatException, IOException {
        final String testString = "4 1.000\n"
        		+ "0.000 0.000 1.000 43.000\n"
        		+ "0.000 0.000 17.000 8.900\n"
        		+ "1.000 1.000 1.360 12.000\n"
        		+ "0.500 0.500 2.000 -0.003\n"
        		+ "2.000 1.000 -0.110 11.000\n"
        		+ "2.500 0.500 -67.000 1.025\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "6\n";      		
        assertEquals(expectedOutput, getOutput());
    }

	
	@Test
    public void testCaseEmpty() throws NumberFormatException, IOException {
        final String testString = "2 1.000\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "0\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseRandomNumbers() throws NumberFormatException, IOException {
        final String testString = "2 5.000\n"
        		+ "-62.100 -33.800\n"
        		+ "-61.800 -71.200\n"
        		+ "-9.300 26.800\n"
        		+ "-76.700 -87.800\n"
        		+ "91.700 10.500\n"
        		+ "47.200 30.300\n"
        		+ "44.600 41.800\n"
        		+ "17.700 30.000\n"
        		+ "39.000 37.900\n"
        		+ "16.700 25.800\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "9\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseSmallDistance() throws NumberFormatException, IOException {
        final String testString = "2 0.001\n"
        		+ "0.000 0.000\n"
        		+ "1.000 1.000\n"
        		+ "0.500 0.500\n"
        		+ "0.501 0.500\n"
        		+ "2.001 1.001\n"
        		+ "2.000 1.000\n"
        		+ "2.500 0.500\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "6\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseBigDistance() throws NumberFormatException, IOException {
        final String testString = "2 100000.000\n"
        		+ "0.000 0.000\n"
        		+ "1.000 1.000\n"
        		+ "0.500 0.500\n"
        		+ "0.501 0.500\n"
        		+ "2.001 1.001\n"
        		+ "2.000 1.000\n"
        		+ "99999.500 50000.500\n";
        provideInput(testString);

        KMeans3.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	
}
