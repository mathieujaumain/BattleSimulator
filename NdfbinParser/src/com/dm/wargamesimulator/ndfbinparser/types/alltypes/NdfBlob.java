package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfBlob extends FlatValueWrapper {
    public NdfBlob(byte[] value) {
        super(NdfType.BLOB, value);
    }
}
