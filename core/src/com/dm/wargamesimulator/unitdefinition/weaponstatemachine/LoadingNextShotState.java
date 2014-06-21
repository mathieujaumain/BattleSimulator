package com.dm.wargamesimulator.unitdefinition.weaponstatemachine;

import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 26/04/14
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class LoadingNextShotState extends WeaponState {

    private long mTimeBetweenShots =0;

    public LoadingNextShotState(IWeaponStateOwner owner) {
        super(owner);
        mTimeBetweenShots = owner.getTimeBetweenShots();
    }

    @Override
    public void execute(long elapsedTime) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void exitState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void enterState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
