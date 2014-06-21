package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfUnknown extends FlatValueWrapper {
    public NdfUnknown( byte[] value) {
        super(NdfType.UNKNOWN, value);
    }
}
