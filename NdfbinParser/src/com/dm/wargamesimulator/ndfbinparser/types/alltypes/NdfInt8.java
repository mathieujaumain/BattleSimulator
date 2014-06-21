package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 20/05/14.
 */
public class NdfInt8 extends FlatValueWrapper {

    public NdfInt8( byte value) {
        super(NdfType.INT8, value);
    }
}
