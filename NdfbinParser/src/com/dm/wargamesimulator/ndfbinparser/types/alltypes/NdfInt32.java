package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 20/05/14.
 */
public class NdfInt32 extends FlatValueWrapper {
    public NdfInt32(long value) {
        super(NdfType.INT32, value);
    }
}
