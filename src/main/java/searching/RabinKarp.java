package searching;

import static java.lang.Math.pow;

public class RabinKarp {

    private static final int FACTOR = 17;
    private static final int MAX_HASH = 1000;

    /**
     * Unfortunately, this is not very practicable because for charSequences of length >= 8 we quickly run
     * into overflow errors.
     */
    static int initialHash(String charSequence) {
        int hash = 0;
        int weight = 1;
        for (int i = 0; i < charSequence.length(); ++i) {
            char character = charSequence.charAt(charSequence.length() - 1 - i);
            if (Integer.MAX_VALUE - (int) pow(weight, i) < character) {
                throw new IllegalArgumentException("Charsequence is too long, overflow encountered");
            }
            hash = (hash % MAX_HASH + (character * weight) % MAX_HASH) % MAX_HASH; // (a + b) % c = (a % c + b % c) % c
            weight = weight * FACTOR;
        }
        return hash;
    }

    /**
     * @param nChars  must be such that 17^(nChars-1) < Integer.MAX_VALUE
     * @param oldHash assumed to be >= 0 and <= {@code maxHash}
     */
    static int rollingHash(int oldHash, int nChars, char leftRemove, char rightAdd) {
        int newHash = oldHash - (leftRemove * (int) pow(FACTOR, nChars - 1)) % MAX_HASH;
        if (newHash < 0) {
            newHash += MAX_HASH;
        }
        newHash = (newHash * FACTOR) % MAX_HASH;
        newHash += rightAdd;
        return newHash % MAX_HASH;
    }
}
