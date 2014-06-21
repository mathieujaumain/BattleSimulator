package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public abstract class WeaponState {

    private final IWeaponStateOwner mOwner;

    public WeaponState(IWeaponStateOwner owner){
        mOwner = owner;
    }

    public abstract void execute(long elapsedTime);
    public abstract void exitState();
    public abstract void enterState();


    public IWeaponStateOwner getOwner(){
        return mOwner;
    }
}
