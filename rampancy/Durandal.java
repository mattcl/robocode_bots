/**
 * Durandal.java
 */
package rampancy;

import java.awt.Color;
import java.awt.Graphics2D;

import rampancy.standard.*;
import rampancy.util.RUtil;
import rampancy.util.weapon.*;
import robocode.BattleEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * @author Matt Chun-Lum
 *
 */
public class Durandal extends RampantRobot {
    
    public static final double MAX_RADAR_TRACKING_AMOUNT = Math.PI / 4.0;
    
    
    public static final RGun[] guns = new RGun[] {
        //new RDCGun(),
        new RAGFGun(),
        //new RPMGun()
        new RCTGun()
    };
    
    public void onPaint(Graphics2D g) {
        super.onPaint(g);
    }
    
    public void run() {
        super.run();
        initialSetup();
        updateState(null);
        targetingManager.updateNewRound();
        
        turnRadarRightRadians(Double.POSITIVE_INFINITY);
        do {
            scan();
        } while (true);
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
        focusRadar(e);
        super.onScannedRobot(e);
    }
    
    public void onBattleEnded(BattleEndedEvent e) {
        if(targetingManager instanceof RDefaultTargetingManager) {
            System.out.println(((RDefaultTargetingManager) targetingManager).getGunStatistics());
        }
    }
    
    // Private Helpers
    private void initialSetup() {
        setColors(Color.black, new Color(0x0D5E10), new Color(0x0D5E10), Color.white, Color.blue);
        // set the enemyManager if necessary
        if(enemyManager == null)
            enemyManager = new RDefaultEnemyManager(this);
        enemyManager.updateReference(this);
        enemyManager.resetAll();
        
        if(waveManager == null)
            waveManager = new RDefaultWaveManager(this);
        waveManager.updateReference(this);
        waveManager.clearWaves();
        
        if(movementManager == null)
            movementManager = new RDefaultMovementManager(this);
        movementManager.updateReference(this);
        
        if(targetingManager == null)
            targetingManager = new RDefaultTargetingManager(guns);
        
        if(statisticsManager == null)
            statisticsManager = new RDefaultStatisticsManager(); 
    }
    
    /**
     * Credit: Voidious
     * Focuses the radar
     * @param e
     */
    private void focusRadar(ScannedRobotEvent e) {
        double radarBearingOffset =  Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians()); 
        setTurnRadarRightRadians(radarBearingOffset);
    }
}
