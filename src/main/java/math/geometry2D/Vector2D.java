package math.geometry2D;

import java.util.Objects;

/**
 * A representation of a vector in 2 dimensions: its two components along the basis vectors which we call x and y basis.
 * <br><br>
 * Caveat: NaN is not allowed.
 * <br><br>
 * Caveat: Negative -0.0 and positive 0.0 are considered equal.
 * <br><br>
 * Caveat: the equals method is implemented using built-in Java equality check for doubles.
 * <br><br>
 * Vector vs Point:
 * Note that a class Point2D would look very similar but imply a restricted usage: a point vector always starts in the
 * origin whereas a vector in general is a more general notion that is independent of a starting point. If we use a
 * vector to define a point in 2D, we suggest to convey this intention by the name of the variable rather than its type.
 */
public record Vector2D(double x, double y) {

    /**
     * Note that in Java the double -0.0 is represented as different bits than 0.0. Therefore, we
     * have some surprising behaviour
     *
     * <pre>
     *     {@code
     * final double d1 = 0d;
     * final double d2 = -0d;
     *
     * System.out.println(d1 == d2); //prints ... true
     * System.out.println(d1 < d2);  //prints ... false
     * System.out.println(d2 < d1);  //prints ... false
     * System.out.println(Double.compare(d1, d2)); //prints ... 1
     * System.out.println(Double.compare(d2, d1)); //prints ... -1
     * System.out.println(new Double(0.0).equals(new Double(-0.0))); // prints ... false
     * }
     * </pre>
     * <p>
     * Since we want the vector (0.0, 0.0) to be considered equal to (-0.0, -0.0)
     * we explicitly have to define the equals method to use == here.
     */
    public static final double POSITIVE_ZERO = 0.0;
    public static final double NEGATIVE_ZERO = -0.0;

    public static final Vector2D ZERO_VECTOR = new Vector2D(POSITIVE_ZERO, POSITIVE_ZERO);
    public static final Vector2D POSITIVE_INFINITY_VECTOR = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector2D NEGATIVE_INFINITY_VECTOR = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    static final double DOUBLE_PRECISION = 10e-12;

    public Vector2D {
        if (Double.isNaN(x)) {
            throw new IllegalArgumentException("X value was NaN, which is not allowed.");
        }
        if (Double.isNaN(y)) {
            throw new IllegalArgumentException("Y value was NaN, which is not allowed.");
        }
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D negative() {
        return new Vector2D(-x, -y);
    }

    public Vector2D subtract(Vector2D other) {
        return add(other.negative());
    }

    public Vector2D scale(double scale) {
        return new Vector2D(x * scale, y * scale);
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D normalize() {
        double length = length();
        return new Vector2D(x / length, y / length);
    }

    /**
     * Intuitively, one might want to consider implementing a tolerance for double comparison here. However, in practice
     * this would imply that the hashCode method would have to be adapted accordingly to give the same hashCode for any
     * two doubles that are considered equal by the equals method. Since that seems highly non-trivial, we have to live
     * with the fact that two doubles must be really the same numbers to be considered equal here.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        // we use the == operator here because we want to consider 0.0 equal as -0.0
        // also note that the == gives false if both or one of the two values is Double.NaN
        return vector2D.x == x && vector2D.y == y;
    }

    @Override
    public int hashCode() {
        double xValueToHash = usePositiveZero(x);
        double yValueToHash = usePositiveZero(y);
        return Objects.hash(xValueToHash, yValueToHash);
    }

    private static double usePositiveZero(double value) {
        return Double.valueOf(value).equals(NEGATIVE_ZERO) ? POSITIVE_ZERO : value;
    }

    public boolean isVirtuallySameDirection(Vector2D other) {
        return Double.compare(Math.abs(x - other.x), DOUBLE_PRECISION) < 0
                && Double.compare(Math.abs(y - other.y), DOUBLE_PRECISION) < 0;
    }
}
