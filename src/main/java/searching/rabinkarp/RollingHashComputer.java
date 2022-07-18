package searching.rabinkarp;

abstract class RollingHashComputer {

    protected final int maxHash;

    protected RollingHashComputer(int maxHash) {
        this.maxHash = maxHash;
    }

    abstract long computeFullHash(String word);

    abstract long rollToRight(long oldHash, char[] text, int oldStartIncl, int nChars);
}
