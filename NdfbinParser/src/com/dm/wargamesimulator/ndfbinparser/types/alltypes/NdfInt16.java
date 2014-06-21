package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 20/05/14.
 */
public class NdfInt16 extends FlatValueWrapper{
    public NdfInt16(int value) {
        super(NdfType.INT16, value);
    }
}
