package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public class AimingWeaponState extends WeaponState {

    private long mAimingTime = 0;

    public AimingWeaponState(IWeaponStateOwner owner) {
        super(owner);
        mAimingTime = owner.getAimingTime();
    }

    @Override
    public void execute(long elapsedTime) {

    }

    @Override
    public void exitState() {

    }

    @Override
    public void enterState() {

    }
}
