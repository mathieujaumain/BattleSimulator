package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inv on 12/05/14.
 */
public class NdfbinClass  {

    public long Id;
    public String name;
    public List<NdfbinProperty> properties = new ArrayList<NdfbinProperty>();
    public List<NdfbinObject> instances = new ArrayList<NdfbinObject>();


    public NdfbinProperty getPropertyOfId(long id){
        for(NdfbinProperty prop : properties){
            if(prop.Id == id)
                return prop;
        }
        return null;
    }

    public NdfbinObject getInstancebyId(long Id){
        for(NdfbinObject instance : instances){
            if(instance.Id == Id)
                return instance;
        }
        return null;
    }
}
