package de.polygondev.hjp.utils;

import java.util.ArrayList;

public class PlayerStatistics {

    private float lifePoints = 0f;
    private float dmgPoints = 0f;
    private boolean hasJetpack = false;
    private float jetpackFuel = 100f;

    /**
     * Gets the Lifepoints of the Player
     * @return float
     */
    public float getLifePoints()
    {
        return lifePoints;
    }
    
    /**
     * Sets the Lifepoints of the Player
     * @param lifePoints
     */
    public void setLifePoints(float lifePoints)
    {
        this.lifePoints = lifePoints;
    }
    
    /**
     * get Damage Points of the Player
     * @return float
     */
    public float getDmgPoints()
    {
        return dmgPoints;
    }
    
    /**
     * sets Damage Points of the Player
     * @param dmgPoints
     */
    public void setDmgPoints(float dmgPoints)
    {
        this.dmgPoints = dmgPoints;
    }

    /**
     * Whether the player has a jetpack or not
     * @return boolean
     */
    public boolean hasJetpack() {
        return this.hasJetpack;
    }

    /**
     * Sets the players jetpack
     * @param val
     */
    public void setHasJetpack(boolean val) {
        this.hasJetpack = val;
    }

    public float getJetpackFuel() {
        return this.jetpackFuel;
    }

    public void setJetpackFuel(float jetpackFuel) {
        this.jetpackFuel = jetpackFuel;
    }
}
