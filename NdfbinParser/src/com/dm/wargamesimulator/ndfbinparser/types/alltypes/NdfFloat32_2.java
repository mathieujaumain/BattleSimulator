package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfFloat32_2 extends NdfSingle {

    public double value2;

    public NdfFloat32_2(float value, float value2) {
        super(value);
        this.value2 = value2;
        type = NdfType.FLOAT32_2;
    }
}
