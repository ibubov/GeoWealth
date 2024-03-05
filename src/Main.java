import java.io.IOException;
import java.util.List;

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
        List<String> answer = utils.checkAllWords(utils.all9LetterWords);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        System.out.println("Number of results" + answer.size());
    }
}
