import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressDeliveryTest {
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
    public void sampleCase()   {
        final String testString = "3 1\n"
        		+ "2 3\n"
        		+ "2 4\n"
        		+ "4 4\n"
        		+ "-1 1 -1\n"
        		+ "-1 -1 1\n"
        		+ "-1 -1 -1\n"
        		+ "1 3\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "0.58\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCase2()   {
        final String testString = "4 3\n"
        		+ "30 60\n"
        		+ "10 1000\n"
        		+ "12 5\n"
        		+ "10 1\n"
        		+ "-1 10 -1 30\n"
        		+ "10 -1 10 -1\n"
        		+ "-1 -1 -1 10\n"
        		+ "15 6 -1 -1\n"
        		+ "2 4\n"
        		+ "3 1\n"
        		+ "3 2\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "0.51 8.01 8.00\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Ignore
    
    
    public void testCaseKeepFlying()   {
        final String testString = "4 3\n"
        		+ "30 100\n"
        		+ "10 10\n"
        		+ "15 90\n"
        		+ "7 5\n"
        		+ "-1 10 15 -1\n"
        		+ "10 -1 -1 -1\n"
        		+ "-1 -1 -1 15\n"
        		+ "10 -1 -1 -1\n"
        		+ "2 3\n"
        		+ "1 4\n"
        		+ "3 4\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "1.15 0.30 0.17\n";      		
        assertEquals(expectedOutput, getOutput());
    }

    
    @Test
    public void testCaseRandomNetwork()   {
        final String testString = "4 6\n"
        		+ "40 100\n"
        		+ "90 10\n"
        		+ "20 80\n"
        		+ "10 60\n"
        		+ "-1 70 10 -1\n"
        		+ "40 -1 70 90\n"
        		+ "50 30 -1 20\n"
        		+ "30 -1 20 -1\n"
        		+ "2 3\n"
        		+ "1 4\n"
        		+ "3 4\n"
        		+ "1 3\n"
        		+ "2 4\n"
        		+ "2 1\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "4.10 0.35 0.25 0.10 4.35 4.00\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void testCaseRandomNetwork2()   {
        final String testString = "4 9\n"
        		+ "40 100\n"
        		+ "90 10\n"
        		+ "20 80\n"
        		+ "40 60\n"
        		+ "-1 70 10 -1\n"
        		+ "40 -1 70 90\n"
        		+ "50 30 -1 20\n"
        		+ "30 -1 20 -1\n"
        		+ "2 3\n"
        		+ "1 4\n"
        		+ "3 4\n"
        		+ "1 3\n"
        		+ "2 4\n"
        		+ "2 1\n"
        		+ "3 1\n"
        		+ "4 1\n"
        		+ "4 3\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "4.10 0.35 0.25 0.10 4.35 4.00 0.75 0.50 0.33\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void testCaseRandomNetwork3()   {
        final String testString = "9 9\n"
        		+ "4 1\n"
        		+ "9 5\n"
        		+ "1 7\n"
        		+ "1 5\n"
        		+ "3 1\n"
        		+ "2 1\n"
        		+ "5 5\n"
        		+ "0 5\n"
        		+ "4 10\n"
        		+ "-1 3 7 3 8 7 1 3 9\n"
        		+ "3 -1 9 5 -1 4 6 1 9\n"
        		+ "1 -1 -1 4 1 5 1 5 4\n"
        		+ "7 -1 2 -1 1 5 3 3 6\n"
        		+ "4 2 1 -1 -1 4 2 1 6\n"
        		+ "-1 6 3 1 5 -1 9 8 -1\n"
        		+ "1 6 4 10 1 3 -1 6 9\n"
        		+ "3 6 10 3 9 8 10 -1 3\n"
        		+ "10 6 5 4 8 10 2 4 -1\n"
        		+ "2 3\n"
        		+ "1 4\n"
        		+ "3 4\n"
        		+ "1 3\n"
        		+ "2 4\n"
        		+ "2 1\n"
        		+ "3 1\n"
        		+ "4 1\n"
        		+ "4 3\n";
        provideInput(testString);

        ExpressDelivery.main(new String[0]);
        
        
        final String expectedOutput = "1.80 2.60 1.74 1.80 1.00 0.60 0.14 1.34 1.20\n";      		
        assertEquals(expectedOutput, getOutput());
    }
}
