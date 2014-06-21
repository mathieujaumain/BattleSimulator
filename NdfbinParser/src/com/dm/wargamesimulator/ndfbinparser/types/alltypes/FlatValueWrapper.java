package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.dm.wargamesimulator.ndfbinparser.types.NdfbinValueWrapper;

/**
 * Created by inv on 20/05/14.
 */
public class FlatValueWrapper extends NdfbinValueWrapper {

    public Object value;

    public FlatValueWrapper(NdfType type, Object value){
        super(type);
        this.value = value;
    }

    public String toString(){
        return value.toString();
    }
}
