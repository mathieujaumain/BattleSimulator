package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinTrans;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfTrans extends FlatValueWrapper {
    public NdfTrans(NdfbinTrans value) {
        super(NdfType.TRANS_TABLE_REFERENCE, value);
    }
}
