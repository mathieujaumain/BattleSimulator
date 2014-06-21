package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public class IdleWeaponState extends WeaponState{

    public IdleWeaponState(IWeaponStateOwner owner) {
        super(owner);
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
