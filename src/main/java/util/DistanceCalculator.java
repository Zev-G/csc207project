package util;


/**
 * This is a class that provides calculation of distance based on input coordinates.
 */
public class DistanceCalculator {

    private static final int R = 6371000;

    /**
     * Calculate the distance based on the input coordinates.
     * @param coord1 Coordinate 1.
     * @param coord2 Coordinate 2.
     * @return the distance between coordinates.
     */
    public static double calculate(double[] coord1, double[] coord2) {

        final double phi1 = coord1[0] * Math.PI / 180;
        final double phi2 = coord2[0] * Math.PI / 180;
        final double dphi = (coord1[0] - coord2[0]) * Math.PI / 180;
        final double dlambda = (coord1[1] - coord2[1]) * Math.PI / 180;
        final double a = Math.sin(dphi / 2) * Math.sin(dphi / 2) + Math.cos(phi1) * Math.cos(phi2)
                * Math.sin(dlambda / 2) * Math.sin(dlambda / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
