package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 26/05/14.
 */
public class NdfZipBlob extends FlatValueWrapper {
    public NdfZipBlob(byte[] value) {
        super(NdfType.ZIP_BLOB, value);
    }
}