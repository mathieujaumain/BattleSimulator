package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfUInt16 extends  FlatValueWrapper {
    public NdfUInt16(long value) {
        super(NdfType.UINT16, value);
    }
}
