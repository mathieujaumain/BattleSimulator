package com.dm.wargamesimulator.unitdefinition;


import com.dm.wargamesimulator.interfaces.IRenderable;
import com.dm.wargamesimulator.interfaces.IUpdatable;

/**
 * Created by inv on 25/04/14.
 */
public class Unit implements IRenderable, IUpdatable {

    public float[] mPosition = new float[2];
    public long mLivingSince = 0;


    @Override
    public void draw() {

    }

    @Override
    public void update(long deltaTimeSinceLastUpdate) {
        mLivingSince += deltaTimeSinceLastUpdate;
    }
}
