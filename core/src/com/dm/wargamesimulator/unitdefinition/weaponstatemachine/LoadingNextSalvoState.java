package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public class LoadingNextSalvoState extends WeaponState {

    private long mTimeBetweenSalvos =0;

    public LoadingNextSalvoState(IWeaponStateOwner owner) {
        super(owner);
        mTimeBetweenSalvos = owner.getTimeBetweenSalvos();
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
