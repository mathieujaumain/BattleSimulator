package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfDouble extends FlatValueWrapper{

    public NdfDouble(double value) {
        super(NdfType.FLOAT64, value);
    }
}
