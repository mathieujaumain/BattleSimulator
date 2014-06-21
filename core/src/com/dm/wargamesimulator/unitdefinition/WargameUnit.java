package com.dm.wargamesimulator.unitdefinition;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.dm.wargamesimulator.interfaces.IUnitStateOwner;
import com.dm.wargamesimulator.unitdefinition.unitstatemachine.UnitState;
/**
 * Created by inv on 24/04/14.
 */
public class WargameUnit extends Unit implements IUnitStateOwner {

    public static final String TAG = "WargameUnit";

    private List<WargameWeapon> mWeaponList = new ArrayList<WargameWeapon>();
    private int mVeterancy = 0;
    private int mMaxHP =10;
    private int mHP = mMaxHP;
    private long mSpeed = 100;
    private long mCurrentSpeed = 0;
    private UnitType mUnitType = UnitType.TYPE_TANK;
    private int mFrontArmor;
    private int mStressLevel = 0;
    private int mMaxStressLevel;


    private UnitState mCurrentState;


    public boolean isAlive(){
       return mHP > 0 ? true:false;
    }

    @Override
    public void changeUnitState(UnitState state) {
        mCurrentState.exitState();
        mCurrentState = state;
        mCurrentState.enterState();
    }

    @Override
    public void addStress(int stress) {
        mStressLevel += stress;
    }

    @Override
    public void removeHP(int HP, WargameUnit attacker) {
        mHP -= HP;
        if(!isAlive()){
            Die(attacker);
        }
    }

    @Override
    public void Die(WargameUnit attacker) {
        Gdx.app.log(TAG, "Unit " + this.toString() + " died from " + attacker.toString());
    }


}
