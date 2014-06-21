package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinStringRef;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfFileNameString extends NdfString {
    public NdfFileNameString(NdfbinStringRef value) {
        super(value);
        type = NdfType.TABLE_STRING_FILE;
    }
}
