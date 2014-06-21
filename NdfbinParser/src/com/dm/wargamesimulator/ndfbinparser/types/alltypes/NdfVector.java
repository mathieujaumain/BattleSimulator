package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.sun.javafx.geom.Vec3f;

/**
 * Created by inv on 21/05/14.
 */
public class NdfVector extends FlatValueWrapper {

    public float value2;
    public float value3;

    public NdfVector(float value, float value2 ,float value3) {
        super(NdfType.VECTOR, value);
        this.value2 = value2;
        this.value3 = value3;
    }
}
