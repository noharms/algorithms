package searching.rabinkarp;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class RabinKarp {

    private static final RollingHashComputer hashComputer = new SimpleRollingHashComputer(1000);

    public static List<Integer> findOccurences(String word, String text) {
        if (word.isEmpty() || text.length() < word.length()) {
            return emptyList();
        }

        long targetHash = hashComputer.computeFullHash(word);
        long currentWindowsHash = hashComputer.computeFullHash(text.substring(0, word.length()));

        List<Integer> matchIndices = new ArrayList<>();
        for (int i = 0; i < text.length() - (word.length() - 1); i++) {
            if (currentWindowsHash == targetHash && word.equals(text.substring(i, i + word.length()))) {
                matchIndices.add(i);
            }
            currentWindowsHash = hashComputer.rollToRight(currentWindowsHash, text.toCharArray(), i, word.length());
        }
        return matchIndices;
    }

}
