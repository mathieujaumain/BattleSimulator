package com.dm.wargamesimulator;

import com.dm.wargamesimulator.interfaces.IRenderable;
import com.dm.wargamesimulator.interfaces.IUpdatable;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 26/04/14
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class MultiverseOfInfinitePossibilities implements IUpdatable, IRenderable{

    private int mNbTestToDo = 0;
    private World mCurrentWorld;


    public void startNewBigBang(World aNewUniverse){
        mCurrentWorld = new World();
    }

    @Override
    public void draw() {
        mCurrentWorld.draw();
    }

    @Override
    public void update(long deltaTimeSinceLastUpdate) {
        mCurrentWorld.update(deltaTimeSinceLastUpdate);
    }
}
