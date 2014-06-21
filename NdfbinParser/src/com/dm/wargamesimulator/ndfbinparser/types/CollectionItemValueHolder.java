package com.dm.wargamesimulator.ndfbinparser.types;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.NdfMap;

/**
 * Created by inv on 21/05/14.
 */
public class CollectionItemValueHolder {

    public NdfbinValueWrapper value;
    public Ndfbin manager;

    public CollectionItemValueHolder(NdfbinValueWrapper value, Ndfbin manager) {
        this.value = value;
        this.manager = manager;
    }


}
