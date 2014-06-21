package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 21/05/14.
 */
public class NdfLocalisationHash extends FlatValueWrapper {
    public NdfLocalisationHash(byte[] value) {
        super(NdfType.LOCALISATION_HASH, value);
    }
}
