package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinStringRef;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfString extends FlatValueWrapper {
    public NdfString(NdfbinStringRef value) {
        super(NdfType.TABLE_STRING, value);
    }
}
