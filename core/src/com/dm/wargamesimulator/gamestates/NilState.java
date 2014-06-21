package com.dm.wargamesimulator.gamestates;

import com.dm.wargamesimulator.interfaces.IGameStateOwner;

/**
 * Created by inv on 18/06/14.
 */
public class NilState extends GameState {

    public NilState(IGameStateOwner owner) {
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
