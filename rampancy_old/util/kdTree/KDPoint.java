/**
 * KDPoint.java
 */
package rampancy_old.util.kdTree;

import java.util.Comparator;

/**
 * This interface specifies methods that kd data points need to implement
 * to be included in a kd tree
 * @author Matt Chun-Lum
 *
 */
public abstract class KDPoint{

    /**
     * @param target
     * @return the complete distance from this KDPoint to the target point
     */
    public abstract double distanceTo(KDPoint target);
}
