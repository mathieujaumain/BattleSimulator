package com.dm.wargamesimulator.ndfbinparser;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinObject;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Created by inv on 13/05/14.
 */
public class Main {

    public static void main(String[] arg) {
        URL uri = null;
        try {
            uri = new URL("file:" + arg[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        NdfbinParser parser = null;
        if (uri != null) {
            try {
                parser = new NdfbinParser(new File(uri.toURI().getSchemeSpecificPart()), false);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        Ndfbin binary;
        if(parser != null){
            binary = parser.parseFile();
            List<NdfbinObject> objectList =binary.
                    getAllInstancesOfClass("TUniteAuSolDescriptor");


            for(NdfbinObject units :objectList){
                NdfString debugName = (NdfString)units.getPropertyByName("ClassNameForDebug").value;

                String unitName = debugName.value.toString().substring(5);
                System.out.println("Unit = " + unitName);
            }
        }

    }

}