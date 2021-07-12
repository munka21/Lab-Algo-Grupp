import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SweetsTest {

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
        final String testString = "5\n"
        		+ "5\n"
        		+ "1 1 1 1 1\n"
        		+ "1 2 3 4 5\n"
        		+ "2 3 4 5 1\n"
        		+ "1 1 1 1 1\n"
        		+ "2 2 2 3 5\n"
        		+ "5 4 3 2 1\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCase2()   {
        final String testString = "5\n"
        		+ "5\n"
        		+ "1 1 1 1 1\n"
        		+ "1 2 3 4 5\n"
        		+ "2 3 4 5 2\n"
        		+ "1 1 1 1 1\n"
        		+ "2 2 2 3 5\n"
        		+ "5 4 3 2 1\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    
    @Test
    public void sampleCaseRandom()   {
        final String testString = "3\n"
        		+ "7\n"
        		+ "4 8 1 9 2 6 5\n"
        		+ "1 3 3 1 3 2 3\n"
        		+ "1 2 2 1 1 3 3\n"
        		+ "2 2 2 2 3 3 1\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCaseMoreKidsThanSweets()   {
        final String testString = "5\n"
        		+ "4\n"
        		+ "1 1 1 1\n"
        		+ "1 2 3 4\n"
        		+ "2 3 4 5\n"
        		+ "1 1 1 1\n"
        		+ "2 2 2 3\n"
        		+ "5 4 3 2\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "6\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCaseOneSweetDisliked()   {
        final String testString = "5\n"
        		+ "5\n"
        		+ "1 1 1 1 1\n"
        		+ "5 2 3 4 5\n"
        		+ "5 3 4 5 1\n"
        		+ "5 1 1 1 1\n"
        		+ "5 2 2 3 5\n"
        		+ "5 4 3 2 1\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "5\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCaseOneSweetDisliked2()   {
        final String testString = "5\n"
        		+ "5\n"
        		+ "4 2 1 1 1\n"
        		+ "5 2 3 4 5\n"
        		+ "5 3 4 5 1\n"
        		+ "5 1 1 1 1\n"
        		+ "5 2 2 3 5\n"
        		+ "5 4 3 2 1\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "2\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCaseOneSweetForEach()   {
        final String testString = "3\n"
        		+ "3\n"
        		+ "1 1 1\n"
        		+ "3 1 2\n"
        		+ "2 3 1\n"
        		+ "1 2 3\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "1\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
    @Test
    public void sampleCaseSameTaste()   {
        final String testString = "3\n"
        		+ "3\n"
        		+ "2 3 2\n"
        		+ "3 1 2\n"
        		+ "3 1 2\n"
        		+ "3 1 2\n";
        provideInput(testString);

        SweetsV2.main(new String[0]);
        
        
        final String expectedOutput = "3\n";      		
        assertEquals(expectedOutput, getOutput());
    }
    
}
