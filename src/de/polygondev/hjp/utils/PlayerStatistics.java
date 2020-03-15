package de.polygondev.hjp.utils;

import java.util.ArrayList;

public class PlayerStatistics {

    private float lifePoints = 0f;
    private float dmgPoints = 0f;
    private boolean hasJetpack = false;
    private float mana = 0;
    private float maxMana = 100f;
    
    //Life points
    public float getLifePoints()
    {
        return lifePoints;
    }
    public void setLifePoints(float lifePoints)
    {
        this.lifePoints = lifePoints;
    }
    
    //Damage points
    public float getDmgPoints()
    {
        return dmgPoints;
    }
    public void setDmgPoints(float dmgPoints)
    {
        this.dmgPoints = dmgPoints;
    }
    
    //Jetpack
    public boolean hasJetpack() {
        return this.hasJetpack;
    }
    public void setHasJetpack(boolean val) {
        this.hasJetpack = val;
    }
    
    //Mana
    public float getMana() {
        return mana;
    }
    public void setMana(float mana) {
        this.mana = mana;
    }
    public boolean addMana(float mana) {
        if (this.mana <= this.maxMana - mana) {
            this.mana += mana;
            return true;
        }
        else {
            this.mana = this.maxMana;
            return false;
        }
    }
    public boolean subMana(float mana) {
        if (this.mana - mana >= 0) {
            this.mana -= mana;
            return true;
        }
        else {
            this.mana = 0;
            return false;
        }
    }
    
    //Max mana
    public float getMaxMana() {
        return maxMana;
    }
    public void setMaxMana(float maxMana) {
        this.maxMana = maxMana;
    }
}
