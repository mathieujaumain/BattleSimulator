package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import com.google.common.primitives.UnsignedLong;

/**
 * Created by inv on 12/05/14.
 */
public class NdfbinHeader {

    public static final String NDFBIN_MAGIC_1 = "EUG0";
    public static final String NDFBIN_MAGIC_2 = "CNDF";
    public static final long IS_COMPRESSED_VALUE = 128;
    public boolean isBodyCompressed;
    public UnsignedLong footerOffset;
    public UnsignedLong headerSize;
    public UnsignedLong fullFileSize;

}
