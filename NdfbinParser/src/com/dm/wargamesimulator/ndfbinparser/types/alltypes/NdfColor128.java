package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfColor128 extends FlatValueWrapper {
    public NdfColor128(byte[] value) {
        super(NdfType.COLOR128, value);
    }
}
