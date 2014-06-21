package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inv on 21/05/14.
 */
public class Ndfbin {

    public NdfbinHeader header = new NdfbinHeader();
    public NdfBinFooter footer = new NdfBinFooter();
    public List<NdfbinClass> classes = new ArrayList<NdfbinClass>();
    public List<NdfbinStringRef> strings = new ArrayList<NdfbinStringRef>();
    public List<NdfbinTrans> trans = new ArrayList<NdfbinTrans>();
    public List<Long> topObjects = new ArrayList<Long>();
    public List<Long> imports = new ArrayList<Long>();
    public List<Long> exports = new ArrayList<Long>();
    public List<NdfbinObject> instances = new ArrayList<NdfbinObject>();



    public NdfbinClass getClassFromId(long Id){
        for(NdfbinClass ndfclass : classes) {
            if(ndfclass.Id == Id)
                return ndfclass;
        }
        return null;
    }


    public List<NdfbinObject> getAllInstancesOfClass(String className){

        for(NdfbinClass aClass : classes){
            if(aClass.name.equals(className))
                return aClass.instances;
        }
        return null;
    }



    public List<NdfbinObject> getInstancesOfClassMatchingPropertyName
            (String className, String propertyName, String propertyStringValue){

        List<NdfbinObject> matchingInstances = new ArrayList<NdfbinObject>();
        List<NdfbinObject> allInstances = getAllInstancesOfClass(className);

        for(NdfbinObject instance : allInstances){
            if(instance.hasPropertyMatchingValue(propertyName, propertyStringValue)){
                matchingInstances.add(instance);
            }
        }
        return matchingInstances;
    }


    public NdfbinObject getInstanceFromIds(long classId, long instanceId){
        List<NdfbinObject> instances = getClassFromId(classId).instances;

        for(NdfbinObject instance : instances){
            if(instance.Id == instanceId)
                return instance;
        }

        return null;
    }
}
