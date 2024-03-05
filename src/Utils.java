import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    String url = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";
    List<String> allWords;
    List<String> all9LetterWords = new ArrayList<>();
    Set<String> all8LetterWords = new HashSet<>();
    Set<String> all7LetterWords = new HashSet<>();
    Set<String> all6LetterWords = new HashSet<>();
    Set<String> all5LetterWords = new HashSet<>();
    Set<String> all4LetterWords = new HashSet<>();
    Set<String> all3LetterWords = new HashSet<>();
    Set<String> all2LetterWords = new HashSet<>();
    Set<String> all1LetterWords = new HashSet<>();

    public void loadAllWords() throws IOException {
        URL wordsUrl = new URL(url);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            allWords = br.lines().skip(2).collect(Collectors.toList());
        }
        allWords.sort(Comparator.comparingInt(String::length));
    }

    public void wordsSeparator() {
        for (String word : allWords) {
            if (word.contains("I") || word.contains("A")) {
                if (word.length() == 9) {
                    all9LetterWords.add(word);
                }

                if (word.length() == 8) {
                    all8LetterWords.add(word);
                }

                if (word.length() == 7) {
                    all7LetterWords.add(word);
                }

                if (word.length() == 6) {
                    all6LetterWords.add(word);
                }

                if (word.length() == 5) {
                    all5LetterWords.add(word);
                }

                if (word.length() == 4) {
                    all4LetterWords.add(word);
                }

                if (word.length() == 3) {
                    all3LetterWords.add(word);
                }

                if (word.length() == 2) {
                    all2LetterWords.add(word);
                }
            }
        }
        all1LetterWords.add("I");
        all1LetterWords.add("A");
    }

public boolean check(String word){
    if (!word.isEmpty()){
        if (word.length() == 1) {
            return checkForWordByLength(word);
        }
        List<String> subMatches = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            String reducedWord = word.substring(0, i) + word.substring(i + 1);
            boolean result = checkForWordByLength(reducedWord);
            if (result) {
                subMatches.add(reducedWord);
            }
        }
        for (String subMatch : subMatches) {
            if (check(subMatch)) {
                return true;
            }
        }
    }
    return false;
}

    public List<String> checkAllWords(List<String> setOfWords) {
        List<String> result = new ArrayList<>();
        for (String word : setOfWords) {
            if (check(word)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean checkForWordByLength(String word){
        return switch (word.length()) {
            case 8 -> all8LetterWords.contains(word);
            case 7 -> all7LetterWords.contains(word);
            case 6 -> all6LetterWords.contains(word);
            case 5 -> all5LetterWords.contains(word);
            case 4 -> all4LetterWords.contains(word);
            case 3 -> all3LetterWords.contains(word);
            case 2 -> all2LetterWords.contains(word);
            case 1 -> all1LetterWords.contains(word);
            default -> allWords.contains(word);
        };
    }
}
