package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public class ShootingWeaponState extends WeaponState {



    public ShootingWeaponState(IWeaponStateOwner owner) {
        super(owner);

    }

    @Override
    public void execute(long elapsedTime) {

         getOwner().changeWeaponState(new LoadingNextShotState(getOwner()));
    }

    @Override
    public void exitState() {

    }

    @Override
    public void enterState() {

    }
}
