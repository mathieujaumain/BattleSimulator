package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;

/**
 * Created by inv on 20/05/14.
 */
public class NdfColor32 extends FlatValueWrapper {

    public byte green;
    public byte blue;
    public byte alpha;

    public NdfColor32(byte red, byte green, byte blue, byte alpha ) {
        super(NdfType.COLOR32, red);
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

}
