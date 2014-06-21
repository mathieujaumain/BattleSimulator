package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import com.google.common.primitives.UnsignedLong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu * Date: 02/05/14
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public class NdfBinFooter {

    public static final String MAGIC =  "TOC0";
    public UnsignedLong offset;
    public long entriesCount;
    public List<FooterEntry> entries = new ArrayList<FooterEntry>();


    public byte[] getFooter(){
        return  null;
    }


    public  FooterEntry findFirstEntryWithName(String name){
        for(FooterEntry entry : entries){
            if(entry.name.contains(name))
                return entry;
        }
        return null;
    }
}
