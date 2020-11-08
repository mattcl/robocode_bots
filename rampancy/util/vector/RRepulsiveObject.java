/**
 * 
 */
package rampancy.util.vector;

import rampancy.util.RPoint;

/**
 * @author Matt Chun-Lum
 *
 */
public interface RRepulsiveObject {
    
    public RVector getForceAtPoint(RPoint point);
}
