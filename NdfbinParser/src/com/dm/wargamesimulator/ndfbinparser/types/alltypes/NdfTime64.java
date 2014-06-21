package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

import java.util.Date;

/**
 * Created by inv on 21/05/14.
 */
public class NdfTime64 extends FlatValueWrapper {
    public NdfTime64(byte[] value) {
        super(NdfType.TIME64, value);
    }
}
