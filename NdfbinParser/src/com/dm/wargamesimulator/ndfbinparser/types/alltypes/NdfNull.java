package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.dm.wargamesimulator.ndfbinparser.types.NdfbinValueWrapper;

/**
 * Created by inv on 21/05/14.
 */
public class NdfNull extends NdfbinValueWrapper {
    public NdfNull() {
        super(NdfType.UNSET);
    }
}
