package com.dm.wargamesimulator;

import com.dm.wargamesimulator.interfaces.IRenderable;
import com.dm.wargamesimulator.interfaces.IUpdatable;
import com.dm.wargamesimulator.unitdefinition.Unit;
import sun.nio.cs.StreamEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inv on 29/04/14.
 */
public class WorldLayer implements IUpdatable, IRenderable {

    private List<Unit> mList = new ArrayList<Unit>();


    public void addToLayer(Unit unit){
        if(!mList.contains(unit)){
            mList.add(unit);
        }
    }

    public void removeFromLayer(Unit unit){
        if(!mList.contains(unit)){
            mList.remove(unit);
        }
    }

    public List<Unit> getLayer(){
        return mList;
    }

    @Override
    public void draw() {
        for(Unit unit : mList){
            unit.draw();
        }

    }

    @Override
    public void update(long deltaTimeSinceLastUpdate) {
        for(Unit unit : mList){
            unit.update(deltaTimeSinceLastUpdate);
        }

    }
}
