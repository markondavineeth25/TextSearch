import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderGenerator {

	private Map<String, List<String>> myMap;
    Random random = new Random();
    
    public OrderGenerator(String inputText) {
        myMap = new HashMap<String, List<String>>();
        String text_lower = inputText.toLowerCase();
        this.orderMessages(text_lower);
        System.out.println("after indexing");
    }
    
    public void orderMessages(String text_lower) {
        List<String> sentences = getSentences(text_lower);
        for (String current : sentences) {
            addToIndex(current);
        }
    }
    
    public List<String> getSentences(String text_lower) {
        String endOfSentenceRegex = "(?<!mr|mrs|dr|ms|Mr|Mrs|Dr|Ms)[\\.\\?\\!]+";
        final String[] lines = text_lower.split(endOfSentenceRegex);
        return Arrays.asList(lines);
    }
    
    public void addToIndex(String inputString) {
        List<String> words = getWords(inputString);

        for (int i = 0; i < words.size() - 1; i++) {
            String currentWordPair = words.get(i) + " " + words.get(i + 1);
            List<String> currentSuggestions = myMap.get(currentWordPair);

            if (currentSuggestions == null) {
                myMap.put(currentWordPair, new ArrayList<String>());
            }
            String possibleNextWord = i + 2 <= words.size() - 1 ? words.get(i + 2) : null;
            if (possibleNextWord != null) {
                if (!myMap.get(currentWordPair).contains(currentWordPair)) {
                    myMap.get(currentWordPair).add(possibleNextWord);
                }
            }
        }
    }
    
    private List<String> getWords(String in) {
        String wordRegex = "([\\w\\']+)";
        Pattern p = Pattern.compile(wordRegex);
        Matcher m = p.matcher(in);
        List<String> words = new ArrayList<>();
        while (m.find()) {
            words.add(m.group(1));
        }
        return words;
    }
    
    public String getRandomNextWord(String key) {
        if (myMap.containsKey(key)) {
            List<String> possibleNextWords = this.myMap.get(key);
            if (possibleNextWords != null && !possibleNextWords.isEmpty()) {
                return possibleNextWords.get(random.nextInt(possibleNextWords.size()));
            }
        }
        return "";
    }

    public String generateNextKey(String key, String nextWord) {
        String[] keyWords = key.split(" ");
        String newKey = keyWords[1] + " " + nextWord;
        return newKey;
    }
}
