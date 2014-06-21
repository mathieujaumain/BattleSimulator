package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfEugInt2 extends FlatValueWrapper {

    public int value2;

    public NdfEugInt2( int value, int value2) {
        super(NdfType.EUGINT2, value);
        this.value2 = value2;
    }
}
