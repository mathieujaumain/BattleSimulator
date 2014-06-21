package com.dm.wargamesimulator.unitdefinition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.dm.wargamesimulator.interfaces.IUpdatable;
import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;
import com.dm.wargamesimulator.unitdefinition.weaponstatemachine.WeaponState;

import java.util.Random;

/**
 * Created by inv on 24/04/14.
 */
public class WargameWeapon implements IWeaponStateOwner, IUpdatable {


    public static final String TAG = "WargameWeapon";

    private int mNbSalvos =1;
    private int mCurrentNbSalvos = mNbSalvos;
    private int mRoundsPerSalvo = 1;
    private int mRoundsInCurrentSalvo;
    private int mNbShotPerSalvo = 1;
    private int mNbSimultaneousRoundsPerShot = 1;


    private long mGroundRange = 0;
    private long mHelRange = 0;
    private long mPlnRange = 0;

    private int mAccuracy = 0;
    private int mAP = 0;
    private int mHE = 0;
    private int mStressDammage = 0;

    private int mTimeBetweenShots = 0;
    private int mTimeBetweenSalvos =0;
    private int mAimingTime =0;

    private float mBulletSpeed;

    private WeaponState mCurrentState;
    private WargameUnit mCurrentTarget = null;


    @Override
    public long getTimeBetweenShots() {
        return mTimeBetweenShots;
    }

    @Override
    public long getTimeBetweenSalvos() {
        return mTimeBetweenSalvos;
    }

    @Override
    public long getAimingTime() {
        return mAimingTime;
    }

    @Override
    public WargameUnit getCurrentTarget() {
        return mCurrentTarget;
    }

    @Override
    public void MissTarget(WargameUnit unit) {
        Gdx.app.log(TAG, "Miss target " + unit.toString());
    }

    @Override
    public void DealDammagesTo(WargameUnit unit) {
        //Compute dammages


        //Result
        if(!unit.isAlive()){
            mCurrentTarget = null;
        }
    }

    @Override
    public void DealStressTo(WargameUnit unit) {
        unit.addStress(mStressDammage);
    }

    @Override
    public void ShotAt(WargameUnit unit) {

        //Compute acc
        float bonuses = 1;
        float dice = MathUtils.random(0, 100);
        if(dice < mAccuracy * bonuses){
            Gdx.app.log(TAG, "Hit target " + unit.toString());
            DealStressTo(unit);
            DealDammagesTo(unit);
        } else {
            MissTarget(unit);
        }


    }

    @Override
    public boolean hasShotsInCurrentSalvos() {
        return mRoundsInCurrentSalvo > 0 ? true:false;
    }

    @Override
    public boolean hasSalvos() {
        return mCurrentNbSalvos > 0 ? true : false;
    }

    @Override
    public void changeWeaponState(WeaponState state) {
        mCurrentState.exitState();
        mCurrentState = state;
        mCurrentState.enterState();
    }


    @Override
    public void update(long deltaTimeSinceLastUpdate) {
        mCurrentState.execute(deltaTimeSinceLastUpdate);
    }
}
