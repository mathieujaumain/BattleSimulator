package com.dm.wargamesimulator.gamestates;

import com.dm.wargamesimulator.interfaces.IGameStateOwner;
import com.dm.wargamesimulator.interfaces.IWeaponStateOwner;

/**
 * Created by inv on 17/06/14.
 */
public abstract class GameState {

    private final IGameStateOwner mOwner;

    public GameState(IGameStateOwner owner){
        mOwner = owner;
    }

    public abstract void execute(long elapsedTime);
    public abstract void exitState();
    public abstract void enterState();


    public IGameStateOwner getOwner(){
        return mOwner;
    }
}
