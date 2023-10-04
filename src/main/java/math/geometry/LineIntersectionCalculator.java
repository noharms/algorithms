package math.geometry;


/**
 * In two dimensions, two straight lines can either have
 * <ol>
 *     <li>0 intersection points (if they are parallel)</li>
 *     <li>1 intersection point</li>
 *     <li>infinite intersection points (if they are the same)</li>
 * </ol>
 *
 * Which case it can simply be computed by solving the system
 *
 * <pre>
 *
 *   f1(x) = a * x + b
 *   f2(x) = c * x + d
 *
 *   -->   a * x + b = c * x + d
 *   <->   x = (d - b) / (a - c)
 *
 *   -->   y = (d - b) / (a - c) * a + b = d * a / (a - c) - b * a / (a - c) + b = d * a / (a - c) + b ( 1 - a / (a-c))
 *           = d * a / (a - c) - c * b / (a - c) = (d * a - c * b) / (a - c)
 *
 * </pre>
 *
 * where a, b, c, d are known.
 */
public class LineIntersectionCalculator {


}
