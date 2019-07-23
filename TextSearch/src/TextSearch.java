import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TextSearch {

	public static void main(String[] args) {
		
		if(args.length < 1){
            System.out.println("enter file as input");
            System.exit(1);
        }
		
		
		String file = args[0];
		String input_text = args[1];
		TextSearch textSearch = new TextSearch();
		String finalText = textSearch.getText(file, input_text);

		System.out.println(finalText);
        
	}

	public String getText(String file, String input_text) {
		String content = getFileContent(file);
		String input = getFileContent(input_text);
		OrderGenerator orderGenerator = new OrderGenerator(content);
		
        String nextWord = orderGenerator.getRandomNextWord(input);
        StringWriter sw = new StringWriter().append(input);

        int wordCount = 2; //They key contains 2 words
        while (nextWord != null && !nextWord.isEmpty() && wordCount < 50){
            sw.append(" ").append(nextWord);
            wordCount++;
            input = orderGenerator.generateNextKey(input, nextWord);
            nextWord = orderGenerator.getRandomNextWord(input);
        }
        return sw.toString();
        
	}

	private String getFileContent(String file) {
		// TODO Auto-generated method stub
		String fileInfo = null;
        try {
        	fileInfo = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
	}
}
