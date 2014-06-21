package com.dm.wargamesimulator;


import com.dm.wargamesimulator.interfaces.IRenderable;
import com.dm.wargamesimulator.interfaces.IUpdatable;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 26/04/14
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class World implements IRenderable, IUpdatable{

    private WorldLayer mBlueForceLayer = new WorldLayer();
    private WorldLayer mRedForceLayer = new WorldLayer();

    private WorldLayer mProjectilesLayer = new WorldLayer();
    private WorldLayer mFXLayer = new WorldLayer();

    public static final float WORLD_WIDTH = 8000;
    public static final float WORLD_HEIGHT = 8000;

    private long mCurrentTime = 0;

    private float mTimeCompressionCoefficient = 1;


    @Override
    public void draw() {

        mBlueForceLayer.draw();
        mRedForceLayer.draw();
        mProjectilesLayer.draw();
        mFXLayer.draw();
    }

    @Override
    public void update(long realDeltaTimeSinceLastUpdate) {
        long compressedDelta =  realDeltaTimeSinceLastUpdate
                * (long)mTimeCompressionCoefficient;
        mCurrentTime += compressedDelta;
        mBlueForceLayer.update(compressedDelta);
        mBlueForceLayer.update(compressedDelta);
        mRedForceLayer.update(compressedDelta);
        mProjectilesLayer.update(compressedDelta);
        mFXLayer.update(compressedDelta);
    }


    public void setTimeCompression(float coeff){
        mTimeCompressionCoefficient = coeff;
    }
}
