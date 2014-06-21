package com.dm.wargamesimulator.interfaces;

import com.dm.wargamesimulator.unitdefinition.WargameUnit;
import com.dm.wargamesimulator.unitdefinition.unitstatemachine.UnitState;

/**
 * Created by inv on 24/04/14.
 */
public interface IUnitStateOwner {

    public void changeUnitState(UnitState state);
    public void addStress(int stress);
    public void removeHP(int HP, WargameUnit attacker);
    public void Die(WargameUnit attacker);
}
