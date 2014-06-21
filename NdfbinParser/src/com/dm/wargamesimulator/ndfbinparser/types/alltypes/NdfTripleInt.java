package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfTripleInt extends FlatValueWrapper{

    public int value2;
    public int value3;

    public NdfTripleInt(int value, int value2, int value3) {
        super(NdfType.TRIPPLE_INT, value);
        this.value2 = value2;
        this.value3 = value3;
    }
}
