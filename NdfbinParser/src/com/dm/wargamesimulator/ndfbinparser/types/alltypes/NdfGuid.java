package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfGuid extends FlatValueWrapper {
    public NdfGuid(byte[] value) {
        super(NdfType.GUID, value);
    }
}
