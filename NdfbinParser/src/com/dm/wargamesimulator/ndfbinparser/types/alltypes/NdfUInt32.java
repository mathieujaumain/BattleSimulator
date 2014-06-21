package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfUInt32 extends FlatValueWrapper {
    public NdfUInt32(long value) {
        super(NdfType.UINT32, value);
    }
}
