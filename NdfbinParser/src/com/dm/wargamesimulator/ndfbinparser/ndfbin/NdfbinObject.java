package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import com.dm.wargamesimulator.ndfbinparser.types.alltypes.FlatValueWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inv on 19/05/14.
 */
public class NdfbinObject {

    public List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
    public long Id;
    public boolean isTOPO;
    public NdfbinClass thisClass;
    public byte[] data;
    public long offset;


    public boolean hasPropertyNamed(String propertyName){
        for(PropertyValue prop : propertyValues){
            if(prop.property.name.equals(propertyName))
                return true;
        }
        return false;
    }

    public boolean hasPropertyMatchingValue(String propertyName, String stringValue){
        if(!hasPropertyNamed(propertyName))
            return false;
        for(PropertyValue prop : propertyValues){
            if(prop.property.name.equals(propertyName)){
                try {
                    FlatValueWrapper wrapper = (FlatValueWrapper) prop.value;
                    if(stringValue.equals(wrapper.value.toString()))
                        return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }


    public PropertyValue getPropertyByName(String propertyName){
        if(!hasPropertyNamed(propertyName))
            return null;
        for(PropertyValue propertyValue : propertyValues){
            if(propertyValue.property.name.equals(propertyName)){
               return propertyValue;
            }
        }
        return null;
    }
}
