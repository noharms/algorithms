package math.geometry2D;


import static math.geometry2D.Vector2D.NEGATIVE_INFINITY_VECTOR;
import static math.geometry2D.Vector2D.POSITIVE_INFINITY_VECTOR;

/**

 * We simply have to solve the system of equations
 *
 * <pre>
 *
 *   ( a )   + t * ( c )   =  ( e ) + s * ( g )
 *   ( b )         ( d )      ( f )       ( h )
 *
 * </pre>
 *
 * where a, b, c, d, e, f, g, h are known and s and t are the line parameters. W.l.o.g, we can solve
 *
 * <pre>
 *
 *   ( a' )   + t * ( c )   =   s * ( g )
 *   ( b' )         ( d )           ( h )
 *
 * </pre>
 *
 *  where a' = a - e, and b' = b - f.
 *
 * <pre>
 *
 *   --> t  =   (s * g  - a') / c
 *
 *   --> b'      + (s * g  - a') / c * d = s * h
 *   <-> c * b'  +  s * g * d - a' * d  = s * h * c
 *   <-> s * (g * d - h * c) = a' * d - b' * c
 *
 *   ==>   s = (a' * d - b' * c) / (g * d - h * c)
 *
 *   and   t = (a' * h - b' * g) / (g * d - h * c)
 * </pre>
 *
 */
public class LineIntersectionCalculator {

    /**
     * If there is 0 intersection points, {@link Vector2D#NEGATIVE_INFINITY_VECTOR} is returned.
     * If there is infinite intersection points, {@link Vector2D#POSITIVE_INFINITY_VECTOR} is returned.
     */
    public static Vector2D intersectionPoint(Line line1, Line line2) {
        LineRelation lineRelation = LineRelation.getLineRelation(line1, line2);
        if (lineRelation.equals(LineRelation.PARALELL)) {
            return NEGATIVE_INFINITY_VECTOR;
        } else if (lineRelation.equals(LineRelation.IDENTICAL)) {
            return POSITIVE_INFINITY_VECTOR;
        } else {
            return intersectionPointTwoCrossingLines(line1, line2);
        }
    }

    private static Vector2D intersectionPointTwoCrossingLines(Line line1, Line line2) {
        double a = line1.getSamplePoint().x();
        double b = line1.getSamplePoint().y();
        double c = line1.getDirection().x();
        double d = line1.getDirection().y();
        double e = line2.getSamplePoint().x();
        double f = line2.getSamplePoint().y();
        double g = line2.getDirection().x();
        double h = line2.getDirection().y();
        double aPrimed = a - e;
        double bPrimed = b - f;

        double t = (aPrimed * h - bPrimed * g) / (g * d - h * c);
        // return x0 + t * direction
        return line1.getSamplePoint().add(line1.getDirection().scale(t));
    }

}
