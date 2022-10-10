package math;

public class GcdComputer {

    public static int gcd(int x, int y) {
        throwIfInvalid(x, y);
        int lesserEq = Math.min(x, y);
        int greaterEq = Math.max(x, y);
        return gcdRecursive(greaterEq, lesserEq);
    }

    private static int gcdRecursive(int greaterEq, int lesserEq) {
        if (lesserEq == 0) {
            return greaterEq;
        } else {
            return gcdRecursive(lesserEq, greaterEq % lesserEq);
        }
    }

    private static void throwIfInvalid(int x, int y) {
        if (x < 0 || y < 0) {
            String msg = "Undefined. Negative numbers not allowed for gcd. (%d %d).".formatted(x, y);
            throw new IllegalArgumentException(msg);
        }
        if (x == 0 && y == 0) {
            throw new IllegalArgumentException("Undefined. Not both arguments can be 0.");
        }
    }
}
