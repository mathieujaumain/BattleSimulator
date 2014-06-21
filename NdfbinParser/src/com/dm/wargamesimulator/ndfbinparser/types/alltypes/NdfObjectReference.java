package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinClass;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinObject;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.dm.wargamesimulator.ndfbinparser.types.NdfbinValueWrapper;

/**
 * Created by inv on 20/05/14.
 */
public class NdfObjectReference extends NdfbinValueWrapper {

    public final boolean isDead;
    public NdfbinClass  thisClass;
    public long instanceId;


    public NdfObjectReference(NdfbinClass cls, long instance) {
        super(NdfType.OBJECT_REFERENCE);
        thisClass = cls;
        instanceId = instance;
        isDead = false;
    }


    public NdfbinObject getObject(){
       return thisClass.getInstancebyId(instanceId);
    }
}
