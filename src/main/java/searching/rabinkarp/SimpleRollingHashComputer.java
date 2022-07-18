package searching.rabinkarp;

class SimpleRollingHashComputer extends RollingHashComputer {

    SimpleRollingHashComputer(int maxHash) {
        super(maxHash);
    }

    @Override
    long computeFullHash(String word) {
        return word.chars().sum() % maxHash;
    }

    @Override
    long rollToRight(long oldHash, char[] text, int oldStartIncl, int nChars) {
        if (oldStartIncl + nChars > text.length - 1) {
            return -1;
        }

        long contributionLeftMostChar = text[oldStartIncl];
        long contributionRemainingChars = maxHash + oldHash - contributionLeftMostChar;
        long contributionNewChar = text[oldStartIncl + nChars];

        long newHash = updateAfterRollout(contributionRemainingChars) + contributionNewChar;
        return newHash % maxHash;
    }

    private long updateAfterRollout(long contributionRemainingChars) {
        return contributionRemainingChars;
    }
}
