import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    String url = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";
    List<String> allWords;
    Set<String> allReducibleWords = new HashSet<>();
    Set<String> all9LetterWords = new HashSet<>();
    Set<String> all8LetterWords = new HashSet<>();
    Set<String> all7LetterWords = new HashSet<>();
    Set<String> all6LetterWords = new HashSet<>();
    Set<String> all5LetterWords = new HashSet<>();
    Set<String> all4LetterWords = new HashSet<>();
    Set<String> all3LetterWords = new HashSet<>();
    List<Set<String>> setOfWordsSets = new ArrayList<>();


    public void loadAllWords() throws IOException {
        URL wordsUrl = new URL(url);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            allWords = br.lines().skip(2).collect(Collectors.toList());
        }
    }

    public void wordsSeparator() {
        for (String word : allWords) {
            if (word.contains("I") || word.contains("A")) {
                switch (word.length()){
                    case 2:
                        allReducibleWords.add(word);
                        break;
                    case 3:
                        all3LetterWords.add(word);
                        break;
                    case 4:
                        all4LetterWords.add(word);
                        break;
                    case 5:
                        all5LetterWords.add(word);
                        break;
                    case 6:
                        all6LetterWords.add(word);
                        break;
                    case 7:
                        all7LetterWords.add(word);
                        break;
                    case 8:
                        all8LetterWords.add(word);
                        break;
                    case 9:
                        all9LetterWords.add(word);
                        break;
                    default:
                        break;
                }
            }
        }
        setOfWordsSets.add(all3LetterWords);
        setOfWordsSets.add(all4LetterWords);
        setOfWordsSets.add(all5LetterWords);
        setOfWordsSets.add(all6LetterWords);
        setOfWordsSets.add(all7LetterWords);
        setOfWordsSets.add(all8LetterWords);
        setOfWordsSets.add(all9LetterWords);
    }

    public void wordProcessor(String word){
        for (int i = 0; i < word.length(); i++) {
            String reducedWord = word.substring(0, i) + word.substring(i + 1);
            if (checkForWordByLength(reducedWord)){
                allReducibleWords.add(word);
                break;
            }
        }
    }

    public void findReducibleWords(Set<String> listOfWords) {
        for (String word : listOfWords) {
            wordProcessor(word);
        }
    }
    private boolean checkForWordByLength(String word){
        return switch (word.length()) {
            case 8 -> all8LetterWords.contains(word);
            case 7 -> all7LetterWords.contains(word);
            case 6 -> all6LetterWords.contains(word);
            case 5 -> all5LetterWords.contains(word);
            case 4 -> all4LetterWords.contains(word);
            case 3 -> all3LetterWords.contains(word);
            default -> allReducibleWords.contains(word);
        };
    }
}
