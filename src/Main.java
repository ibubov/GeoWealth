import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

       Utils utils = new Utils();
        try {
            utils.loadAllWords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        utils.wordsSeparator();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < utils.setOfWordsSets.size(); i++) {
            utils.findReducibleWords(utils.setOfWordsSets.get(i));
        }

        Set<String> solution = new HashSet<>();
        for (String word : utils.allReducibleWords) {
            if (word.length() == 9) {
                solution.add(word);
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " Milliseconds");
        System.out.println("Number of 9 letter words reducible to 1: " + solution.size());
    }
}
