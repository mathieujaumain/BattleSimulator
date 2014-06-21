package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 20/05/14.
 */
public class NdfBoolean extends FlatValueWrapper {


    public NdfBoolean(boolean value) {
        super(NdfType.BOOLEAN, value);
    }
}
