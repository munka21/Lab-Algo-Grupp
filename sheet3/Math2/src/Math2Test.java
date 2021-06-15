import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Math2Test {

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
        		+ "1 1 1\n"
        		+ "88 1136 5298\n"
        		+ "42 420 842\n";
        provideInput(testString);

        Math2.main(new String[0]);
        
        
        final String expectedOutput = "empty set\n"
        		+ "1 16 71\n"
        		+ "1 20 21\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseBigNumber() throws NumberFormatException {
        final String testString = "1\n"
        		+ "244480894 5000000000000000000 59604719598382986 \n";
        provideInput(testString);

        Math2.main(new String[0]);
        
        
        final String expectedOutput = "78125 262144 244140625\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	/*@Test
	public void testPrimeFactoring() {
		Long testNumber=Long.parseLong("5000000000000000000");
		ArrayList<Long> retval=Math2.primeFactors(testNumber);
		ArrayList<Long> expectedOutput= new ArrayList<Long>();
		expectedOutput.add((long) 1);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 2);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		expectedOutput.add((long) 5);
		
		for(int l=0; l< expectedOutput.size();l++) {
			assertEquals(expectedOutput.get(l), retval.get(l));
			
		}
		}*/
}
