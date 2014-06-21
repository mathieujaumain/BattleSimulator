package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.ndfbinparser.types.MapValueHolder;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.dm.wargamesimulator.ndfbinparser.types.NdfTypeManager;
import com.dm.wargamesimulator.ndfbinparser.types.NdfbinValueWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inv on 20/05/14.
 */
public class NdfMap extends FlatValueWrapper {

    public final List<NdfType> typesSelection = new ArrayList<NdfType>();

    public MapValueHolder key;
    public Ndfbin binary;

    public NdfType keyType = NdfType.UNSET;
    public NdfType valueType = NdfType.UNSET;


    public NdfMap(MapValueHolder key, MapValueHolder value, Ndfbin bin) {
        super(NdfType.MAP, value);

        this.key = key;
        this.binary = bin;

        NdfType[] types = NdfTypeManager.getTypeSelection();
        int count = types.length;
        for(int i = 0; i < count; i++){
            typesSelection.add(types[i]);
        }
    }

    public NdfbinValueWrapper getValue(){
        MapValueHolder holder = (MapValueHolder)value;
        return holder.value;
    }

}
