package com.dm.wargamesimulator.unitdefinition.unitstatemachine;

import com.dm.wargamesimulator.interfaces.IUnitStateOwner;

/**
 * Created by inv on 24/04/14.
 */
public abstract class UnitState {

    private final IUnitStateOwner mOwner;

    public UnitState(IUnitStateOwner owner){
        mOwner = owner;
    }

    public abstract void execute();
    public abstract void exitState();
    public abstract void enterState();


    public IUnitStateOwner getOwner(){
        return mOwner;
    }
}
