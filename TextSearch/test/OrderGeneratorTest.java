import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OrderGeneratorTest {

    private OrderGenerator orderGenerator;
    private String text = "Mr. Jones, of the Manor Farm, had locked the hen-houses for the night, but\n" + 
    		"was too drunk to remember to shut the pop-holes. With the ring of light\n" + 
    		"from his lantern dancing from side to side, he lurched across the yard,\n" + 
    		"kicked off his boots at the back door, drew himself a last glass of beer\n" + 
    		"from the barrel in the scullery, and made his way up to bed, where\n" + 
    		"Mrs. Jones was already snoring.";

    @Before
    public void setUp() throws Exception {
        orderGenerator = new OrderGenerator(this.text);
    }

    @Test
    public void testGetSentences() throws Exception {
        List<String> sentences = orderGenerator.getSentences(text);
        assertTrue(sentences.contains("Mr. Jones, of the Manor Farm, had locked the hen-houses for the night, but\n" + 
        		"was too drunk to remember to shut the pop-holes"));
    }

    @Test
    public void testGenerateNextKey() throws Exception {
    		assertEquals("dancing from", orderGenerator.generateNextKey("lantern dancing", "from"));
    		assertEquals("was too", orderGenerator.generateNextKey("but was", "too"));
    }
    


}