import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadMaintenanceTest {

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
    public void testCaseSample() throws NumberFormatException, IOException {
        final String testString = "5\n"
        		+ "0 1\n"
        		+ "2 0\n"
        		+ "0 -2\n"
        		+ "-1 -1\n"
        		+ "-2 0\n";
        provideInput(testString);

        RoadMaintenance.main(new String[0]);
        
        
        final String expectedOutput = "10";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseintersectionisEndPoint() throws NumberFormatException, IOException {
        final String testString = "5\n"
        		+ "0 1\n"
        		+ "2 1\n"
        		+ "0 -2\n"
        		+ "-1 -1\n"
        		+ "-2 0\n";
        provideInput(testString);

        RoadMaintenance.main(new String[0]);
        
        
        final String expectedOutput = "11";      		
        assertEquals(expectedOutput, getOutput());
    }

}
