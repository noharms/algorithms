package math.geometry2D;

/**
 * In two dimensions, two straight lines can have three relations with respect to intersection
 * <ol>
 *     <li>0 intersection points (if they are parallel)</li>
 *     <li>1 intersection point</li>
 *     <li>infinite intersection points (if they are the same)</li>
 * </ol>
 */
public enum LineRelation {
    PARALELL,
    CROSSING,
    IDENTICAL;

    public static LineRelation getLineRelation(Line line1, Line line2) {
        if (line1.isVerticalLine() || line2.isVerticalLine()) {
            return getRelationAtLeastOneVertical(line1, line2);
        } else {
            return getRelationNoneVertical(line1, line2);
        }
    }

    private static LineRelation getRelationAtLeastOneVertical(Line line1, Line line2) {
        if (line1.isVerticalLine() && line2.isVerticalLine()) {
            return getRelationBothVertical(line1, line2);
        } else {
            return CROSSING;
        }
    }

    private static LineRelation getRelationBothVertical(Line line1, Line line2) {
        return line1.getSamplePoint().x() == line2.getSamplePoint().x() ? IDENTICAL : PARALELL;
    }

    private static LineRelation getRelationNoneVertical(Line line1, Line line2) {
        if (isSameDirection(line1, line2)) {
            return containsSamplePoint(line1, line2.getSamplePoint()) ? IDENTICAL : PARALELL;
        } else {
            return CROSSING;
        }
    }

    private static boolean isSameDirection(Line line1, Line line2) {
        Vector2D normalizedDirection1 = line1.getDirection().normalize();
        Vector2D normalizedDirection2 = line2.getDirection().normalize();
        return normalizedDirection1.isVirtuallySameDirection(normalizedDirection2) ||
                normalizedDirection1.isVirtuallySameDirection(normalizedDirection2.negative());
    }

    private static boolean containsSamplePoint(Line line1, Vector2D samplePointLine2) {
        return line1.toLineFunction().valueAt(samplePointLine2.x()) == samplePointLine2.y();
    }
}
