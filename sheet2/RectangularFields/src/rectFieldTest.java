import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class rectFieldTest {

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
        final String testString = "3\n"
        		+ "111\n"
        		+ "110\n"
        		+ "011\n";
        provideInput(testString);

        rectfield.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
	
	@Test
    public void testCaseFullGrid() throws NumberFormatException, IOException {
		String s="";
		for(int i=0; i<200;i++) {
			for(int j=0; j<200;j++) {
				s= s+"1";
			}
			s=s+"\n";
		}
        final String testString = "100\n"
        		+ s;
        provideInput(testString);

        rectfield.main(new String[0]);
        
        
        final String expectedOutput = "\n";      		
        assertEquals(expectedOutput, getOutput());
    }


}
