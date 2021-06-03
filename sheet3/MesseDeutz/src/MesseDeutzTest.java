import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MesseDeutzTest {
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
        final String testString = "7 6\n"
        		+ "0 1\n"
        		+ "1 2\n"
        		+ "3 4\n"
        		+ "3 5\n"
        		+ "4 5\n"
        		+ "5 6\n";
        provideInput(testString);

        MesseDeutz.main(new String[0]);
        
        
        final String expectedOutput = "3\n";      		
        assertEquals(expectedOutput, getOutput());
    }
}
