package searching.rabinkarp;

import static java.lang.Math.pow;

public class PolynomialRollingHashComputer extends RollingHashComputer {

    private final int primeFactor;

    protected PolynomialRollingHashComputer(int maxHash, int primeFactor) {
        super(maxHash);
        this.primeFactor = primeFactor;
    }


    /**
     * Unfortunately, this is not very practicable because for words of length >= 8 we quickly run
     * into overflow errors.
     */
    @Override
    long computeFullHash(String word) {
        int hash = 0;
        int weight = 1;
        for (int i = 0; i < word.length(); ++i) {
            char character = word.charAt(word.length() - 1 - i);
            if ((character * weight) / weight != character) { // overflow detection
                throw new IllegalArgumentException("word is too long, overflow encountered");
            }
            hash = (hash % maxHash + (character * weight) % maxHash) % maxHash; // (a + b) % c = (a % c + b % c) % c

            if ((int) pow(primeFactor, i) / primeFactor != (int) pow(primeFactor, i - 1)) { // overflow detection
                throw new IllegalArgumentException("word is too long, overflow encountered");
            }
            weight = weight * primeFactor;
        }
        return hash;
    }

    /**
     * @param oldHash assumed to be >= 0 and <= {@code maxHash}
     * @param nChars  must be such that 17^(nChars-1) < Integer.MAX_VALUE
     * @return the hash after moving the window one character to the right, or -1, if the passed index combination
     * would be outside of the text
     */
    @Override
    long rollToRight(long oldHash, char[] text, int oldStartIncl, int nChars) {
        if (oldStartIncl + nChars > text.length - 1) {
            return -1;
        }

        char leftRemove = text[oldStartIncl];
        int contributionLeftMostChar = (leftRemove * (int) pow(primeFactor, nChars - 1)) % maxHash;
        int contributionRemainingChars = maxHash + (int) oldHash - contributionLeftMostChar;
        int contributionNewChar = text[oldStartIncl + nChars];

        int newHash = updateAfterRollout(contributionRemainingChars) + contributionNewChar;
        return newHash % maxHash;
    }

    private int updateAfterRollout(int contributionRemainingChars) {
        return (contributionRemainingChars * primeFactor) % maxHash;
    }

}
