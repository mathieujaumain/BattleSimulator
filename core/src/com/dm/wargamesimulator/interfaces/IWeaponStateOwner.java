package com.dm.wargamesimulator.interfaces;

import com.dm.wargamesimulator.unitdefinition.WargameUnit;
import com.dm.wargamesimulator.unitdefinition.weaponstatemachine.WeaponState;

/**
 * Created by inv on 24/04/14.
 */
public interface IWeaponStateOwner {


    public long getTimeBetweenShots();
    public long getTimeBetweenSalvos();
    public long getAimingTime();
    public WargameUnit getCurrentTarget();
    public void MissTarget(WargameUnit unit);
    public void DealDammagesTo(WargameUnit unit);
    public void DealStressTo(WargameUnit unit);
    public void ShotAt(WargameUnit unit);
    public boolean hasShotsInCurrentSalvos();
    public boolean hasSalvos();

    public void changeWeaponState(WeaponState state);


}
