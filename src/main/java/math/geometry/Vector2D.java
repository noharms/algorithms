package math.geometry;

import java.util.Objects;

/**
 * NaN is not allowed.
 * Negative -0.0 and positive 0.0 are considered equal.
 */
public record Vector2D(double x, double y) {

    public static final double POSITIVE_ZERO = 0.0;
    public static final double NEGATIVE_ZERO = -0.0;

    public static final Vector2D ZERO_VECTOR = new Vector2D(POSITIVE_ZERO, POSITIVE_ZERO);
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

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D normalize() {
        double length = length();
        return new Vector2D(x / length, y / length);
    }

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
}
