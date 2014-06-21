package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfSingle extends FlatValueWrapper {
    public NdfSingle(float value) {
        super(NdfType.FLOAT32, value);
    }

    public String toString()
    {
        return String.format("{0:0.####################}", value);
    }
}
