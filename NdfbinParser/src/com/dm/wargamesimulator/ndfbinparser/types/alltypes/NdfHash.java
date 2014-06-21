package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfHash extends FlatValueWrapper {
    public NdfHash(byte[] value) {
        super(NdfType.HASH, value);
    }
}
