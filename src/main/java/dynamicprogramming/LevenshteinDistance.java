package dynamicprogramming;

/**
 * Given two strings word1 and word2, the Levenshtein distance is defined as the minimal number of edit operations
 * necessary to convert word1 to word2 (or equivalently vice versa).
 * <br><br>
 * The allowed edit operations are
 * <ul>
 *     <li>insert a new character</li>
 *     <li>delete an existing character</li>
 *     <li>substitute an existing character</li>
 * </ul>
 * <br><br>
 * For example, given the two strings "abc" and "a", we need two delete operations to convert "abc" to "a", so the
 * Levenshtein distance of the two strings is 2. Similarly, given "abc" and "axc" we only need to substitute the 'b'
 * for the 'x' to convert the strings, which means the distance is just 1.
 */
public class LevenshteinDistance {

    private final String word1;
    private final String word2;

    public LevenshteinDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    public int compute() {
        int[][] subStringDistances = computeSubStringDistances();
        return subStringDistances[word1.length()][word2.length()];
    }

    /**
     * The substring distances matrix has to be understood in the following way: its dimensions are defined by the
     * lengths of the two words, say n x m. The entry at [i, j] in the matrix defines the distance of
     * word1.substring(0, i), with i exclusive, to the word2.substring(0, j), with j exclusive. So as an example, given
     * "abcde" and "xycd", the entry subStringDistance[3][2] = distance("abc", "xy"). Similarly,
     * subStringDistances[3][0] = distance("abc", ""). Given this logic, it is trivial to compute the 0th row and the
     * 0th column of the subStringDistances matrix.
     */
    private int[][] computeSubStringDistances() {
        int nRows = word1.length() + 1; // + 1 because we also want one row for the empty string
        int nCols = word2.length() + 1; // + 1 because we also want one col for the empty string
        int[][] subStringDistances = new int[nRows][nCols];
        setDistancesEmptyStringToWord2(subStringDistances);
        setDistancesWord1ToEmptyString(subStringDistances);
        setNonTrivialDistancesDynamically(subStringDistances);
        return subStringDistances;
    }

    // distances from the empty string to substrings of word2 are stored in the 0th row, so this method fills that row
    private void setDistancesEmptyStringToWord2(int[][] subStringDistances) {
        int nCols = subStringDistances[0].length;
        for (int j = 0; j < nCols; j++) {
            subStringDistances[0][j] = j;
        }
    }

    // distances from substrings of word1 to the empty string are stored in the 0th col, so this method fills that col
    private void setDistancesWord1ToEmptyString(int[][] subStringDistances) {
        int nRows = subStringDistances.length;
        for (int i = 0; i < nRows; i++) {
            subStringDistances[i][0] = i;
        }
    }

    private void setNonTrivialDistancesDynamically(int[][] subStringDistances) {
        int nRows = subStringDistances.length;
        int nCols = subStringDistances[0].length;
        for (int i = 1; i < nRows; i++) {
            for (int j = 1; j < nCols; j++) {
                int distanceBothSmaller = subStringDistances[i - 1][j - 1];
                // since we added the empty strings, the row at i == 1 is representing the char at index 0 in word1,
                // and analogously for other chars: therefore subtract 1 here
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    subStringDistances[i][j] = distanceBothSmaller;
                } else {
                    int distanceSmallerSubstring1 = subStringDistances[i][j - 1];
                    int distanceSmallerSubstring2 = subStringDistances[i - 1][j];
                    int min = minOfThree(distanceBothSmaller, distanceSmallerSubstring1, distanceSmallerSubstring2);
                    subStringDistances[i][j] = 1 + min;
                }
            }
        }
    }

    private int minOfThree(int one, int two, int three) {
        return Math.min(Math.min(one, two), three);
    }
}
