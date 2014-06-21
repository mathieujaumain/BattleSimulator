package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfWideString extends FlatValueWrapper {
    public NdfWideString( String value) {
        super(NdfType.WIDE_STRING, value);
    }
}
