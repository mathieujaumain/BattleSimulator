package com.dm.wargamesimulator.ndfbinparser;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.*;
import com.dm.wargamesimulator.ndfbinparser.types.*;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.FlatValueWrapper;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.NdfCollection;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.NdfMap;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.NdfMapList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.InflaterInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 02/05/14
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class NdfbinParser {

    private boolean mDebug = false;
    private File mNdfBinFile;
    private Ndfbin mNdfbin = new Ndfbin();


    private ByteArrayInputStream mReader;
    private byte[] buffer;
    private long offset= 0;
    private long dataLenght;
    private long currentOffset = 0;

    public NdfbinParser(File mNdfBinFile, boolean debug) {
        this.mNdfBinFile = mNdfBinFile;
        mDebug = debug;
        try {
            byte[] data = Files.readAllBytes(Paths.get(mNdfBinFile.getAbsolutePath()));
            dataLenght = data.length;
            mReader = new ByteArrayInputStream(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Ndfbin parseFile() {

        try {
            readHeader();
            if(mNdfbin.header.isBodyCompressed)
               uncompressBody();
            readFooter();
            readClasses();
            readProperties();
            readStrings();
            readTrans();
            readTopObjects();
            readObjects();

        } catch (Exception e) {
            if(mDebug)
                System.out.print("An error happened :  "+e.toString());
            e.printStackTrace();
            return null;
        }

        return mNdfbin;

    }


    private void readFooter() throws Exception {
        if(mNdfbin.header.footerOffset.longValue() == 0 ||
                mNdfbin.header.footerOffset.longValue() > mNdfBinFile.getTotalSpace())
            throw new Exception("Invalid footer offset.");

        buffer = new byte[4];
        mReader.reset();
        mReader.skip(mNdfbin.header.footerOffset.longValue());
        mReader.read(buffer);
        if(!ByteUtils.toString(buffer).equals(NdfBinFooter.MAGIC))
            throw new InvalidObjectException("Wrong footer \"magic\" = " + ByteUtils.toString(buffer));

        mReader.read(buffer);
        mNdfbin.footer.entriesCount = ByteUtils.toUInt32(buffer);

        if(mDebug)
            System.out.println("*************************************" +
                             "\n           FOOTER INTELS            "
                             +"\nNb Entries = " + mNdfbin.footer.entriesCount);

        buffer = new byte[8];
        for(int i = 0; i < mNdfbin.footer.entriesCount; i++){

            FooterEntry newEntry = new FooterEntry();
            mReader.read(buffer);
            newEntry.name = ByteUtils.toString(buffer);
            mReader.read(buffer);
            newEntry.offset = ByteUtils.toULong(buffer);
            mReader.read(buffer);
            newEntry.size = ByteUtils.toULong(buffer);

            if(mDebug)
                System.out.println(" ---Entry "+ i +
                                    "\nName = " + newEntry.name +
                                    "\nOffset = " + newEntry.offset +
                                    "\nSize = " + newEntry.size);


            mNdfbin.footer.entries.add(newEntry);
        }
        if(mDebug)
            System.out.println("*************************************");



    }

    private void readHeader() throws IOException {
        buffer = new byte[4];
        mReader.read(buffer);

        if(!ByteUtils.toString(buffer).equals(NdfbinHeader.NDFBIN_MAGIC_1)) {
            throw new InvalidObjectException("File isn't an ndfbin file : magic = " + ByteUtils.toString(buffer));
        } else {
            mReader.read(buffer);
            mReader.read(buffer);
            if(!ByteUtils.toString(buffer).equals(NdfbinHeader.NDFBIN_MAGIC_2))
                throw new InvalidObjectException("File isn't an ndfbin file : magic 2 = " + ByteUtils.toString(buffer));

            mReader.read(buffer);
            if(ByteUtils.toUInt32(buffer) == NdfbinHeader.IS_COMPRESSED_VALUE) {
                mNdfbin.header.isBodyCompressed = true;
            } else {
                mNdfbin.header.isBodyCompressed = false;
            }
            buffer = new byte[8];
            mReader.read(buffer);
            mNdfbin.header.footerOffset = ByteUtils.toULong(buffer);
            mReader.read(buffer);
            mNdfbin.header.headerSize = ByteUtils.toULong(buffer);
            mReader.read(buffer);
            mNdfbin.header.fullFileSize = ByteUtils.toULong(buffer);

            if(mDebug)
                System.out.println("*******************************" +
                                   "\n       HEADER INTELS" +
                                   "\nIs Compressed =" + mNdfbin.header.isBodyCompressed+
                                   "\nFooter offset = " + mNdfbin.header.footerOffset +
                                   "\nHeader size = " + mNdfbin.header.headerSize +
                                   "\nFull file size = " + mNdfbin.header.fullFileSize +
                                  "\n******************************");
        }
        
    }


    private long readCHNK() throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("CHNK").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;
        buffer = new byte[4];
        mReader.read(buffer);
        currentOffset += 4;
        mReader.read(buffer);
        currentOffset += 4;
        return ByteUtils.toUInt32(buffer);
    }

    public void readObjects()throws  IOException{

        List<NdfbinObject> objects = new ArrayList<NdfbinObject>();
        long nbObjects = readCHNK();
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("OBJE").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        for (long i = 0; i < nbObjects; i++)
        {

            long objOffset = currentOffset;
            NdfbinObject obj = readObject(i);
            obj.offset = objOffset;

            objects.add(obj);
        }
        mNdfbin.instances.addAll(objects);

    }



    private void readTrans() throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("TRAN").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("TRAN").size.longValue();
        buffer = new byte[4];
        long strgId = 0;
        if(mDebug)
            System.out.println("*************************************" +
                    "\n           TRAN            ");

        while(currentOffset < entryOffset + entrySize ) {
            NdfbinTrans tran = new NdfbinTrans();
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long stringLenght = ByteUtils.toUInt32(buffer);
            buffer = new byte[(int)stringLenght];
            mReader.read(buffer);
            currentOffset += stringLenght;
            tran.value = ByteUtils.toString(buffer);
            tran.Id = strgId;
            strgId++;
            if(mDebug)
                System.out.println("Tran " + tran.Id + " = " + tran);
            mNdfbin.trans.add(tran);


        }
        if(mDebug)
            System.out.println("*************************************");

    }

    private void uncompressBody() throws IOException, DataFormatException {

        ByteArrayOutputStream writeStream = new ByteArrayOutputStream();
        buffer = new byte[mNdfbin.header.headerSize.intValue()];
        mReader.reset();
        mReader.read(buffer);
        writeStream.write(buffer);

        buffer = new byte[4];
        mReader.read(buffer);
        long compressedBloblenght = ByteUtils.toUInt32(buffer);
        System.out.println("Compressed blob lenght = " + compressedBloblenght);

        buffer = new byte[(int)compressedBloblenght];
        mReader.read(buffer);
        System.out.println("buffer size = " + buffer.length);
        InflaterInputStream decompressStream = new InflaterInputStream(new ByteArrayInputStream(buffer));
        int c = 0;
        long count = mNdfbin.header.headerSize.longValue() + 4;
        try{
            while ((c = decompressStream.read()) != -1) {
                writeStream.write(c);
                count++;

            }
        } catch( Exception e){
            System.out.println("Current offset = " + count);
            e.fillInStackTrace();
        }
        decompressStream.close();
        mReader.close();
        mReader = new ByteArrayInputStream(writeStream.toByteArray());
        writeStream.close();
    }


    public void readClasses() throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("CLAS").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("CLAS").size.longValue();
        if(mDebug)
            System.out.println("**************************************"
                             + "\n        CLASSES                   ");
        long count = 0;
        while(currentOffset < entryOffset + entrySize ) {
            NdfbinClass ndfbinClass = new NdfbinClass();
            buffer = new byte[4];
            currentOffset += 4;
            mReader.read(buffer);
            long stringLenght = ByteUtils.toUInt32(buffer);
            buffer = new byte[(int)stringLenght];
            currentOffset += stringLenght;
            mReader.read(buffer);
            ndfbinClass.name = ByteUtils.toString(buffer);
            ndfbinClass.Id = count;
            count++;
            if(mDebug)
                System.out.println("Class " + count + " : " + ndfbinClass.name);
            mNdfbin.classes.add(ndfbinClass);
        }
        if(mDebug)
            System.out.println("**************************************");
    }

    private  void readTopObjects()throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("TRAN").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("TRAN").size.longValue();
        buffer = new byte[4];
        if(mDebug)
            System.out.println("*************************************" +
                    "\n           PROP            ");

        while(currentOffset < entryOffset + entrySize ) {
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long indexTopObject = ByteUtils.toUInt32(buffer);
            if(mDebug)
                System.out.println("TOPO : CLASS " + indexTopObject);
            mNdfbin.topObjects.add(indexTopObject);

        }
        if(mDebug)
            System.out.println("**************************************");

    }

    private  void readImport()throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("IMPR").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("IMPR").size.longValue();
        buffer = new byte[4];
        if(mDebug)
            System.out.println("*************************************" +
                    "\n           IMPR            ");

        while(currentOffset < entryOffset + entrySize ) {
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long anImport = ByteUtils.toUInt32(buffer);
            System.out.println("IMPR : IMPR " + anImport);
            mNdfbin.imports.add(anImport);

        }
        if(mDebug)
            System.out.println("**************************************");

    }


    private  void readExport()throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("EXPR").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("EXPR").size.longValue();
        buffer = new byte[4];
        if(mDebug)
            System.out.println("*************************************" +
                    "\n           EXPR            ");

        while(currentOffset < entryOffset + entrySize ) {
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long anExport = ByteUtils.toUInt32(buffer);
            System.out.println("EXPR : EXPR " + anExport);
            mNdfbin.exports.add(anExport);

        }
        if(mDebug)
            System.out.println("**************************************");

    }

    private void readProperties() throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("PROP").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("PROP").size.longValue();
        buffer = new byte[4];
        long propertyId = 0;
        if(mDebug)
            System.out.println("*************************************" +
                             "\n           PROP            ");

        while(currentOffset < entryOffset + entrySize ) {
            NdfbinProperty property = new NdfbinProperty();
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long stringLenght = ByteUtils.toUInt32(buffer);
            buffer = new byte[(int)stringLenght];
            mReader.read(buffer);
            currentOffset += stringLenght;
            property.name = ByteUtils.toString(buffer);
            property.Id = propertyId;
            propertyId++;
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset +=4;
            long ownerId = ByteUtils.toUInt32(buffer);
            if(mDebug)
                System.out.println("Property " + property.Id + " = " + property.name);

            getClassFromId(ownerId).properties.add(property);

        }
        if(mDebug)
            System.out.println("*************************************");

    }

    public void readStrings() throws IOException {
        mReader.reset();
        long entryOffset = mNdfbin.footer.findFirstEntryWithName("STRG").offset.longValue();
        mReader.skip(entryOffset);
        currentOffset = entryOffset;

        long entrySize = mNdfbin.footer.findFirstEntryWithName("STRG").size.longValue();
        buffer = new byte[4];
        long strgId = 0;
        if(mDebug)
            System.out.println("*************************************" +
                    "\n           STRG            ");

        while(currentOffset < entryOffset + entrySize ) {
            NdfbinStringRef strgRef = new NdfbinStringRef();
            buffer = new byte[4];
            mReader.read(buffer);
            currentOffset += 4;
            long stringLenght = ByteUtils.toUInt32(buffer);
            buffer = new byte[(int)stringLenght];
            mReader.read(buffer);
            currentOffset += stringLenght;
            strgRef.value = ByteUtils.toString(buffer);
            strgRef.Id = strgId;
            strgId++;
            if(mDebug)
                System.out.println("String " + strgRef.Id + " = " + strgRef);
            mNdfbin.strings.add(strgRef);


        }
        if(mDebug)
            System.out.println("*************************************");

    }


    private NdfbinClass getClassFromId(long id) {
        for(NdfbinClass ndfclass : mNdfbin.classes) {
            if(ndfclass.Id == id)
                return ndfclass;
        }
        return null;
    }

    
    /**
     * Read an return object of Id "objId"
     * @param objId
     * @return
     * @throws IOException
     */
    private NdfbinObject readObject(long objId) throws IOException {

        NdfbinObject thisInstance = new NdfbinObject();
        thisInstance.Id = objId;
        thisInstance.isTOPO = mNdfbin.topObjects.contains(thisInstance.Id) ? true:false;

        buffer = new byte[4];
        mReader.read(buffer);
        currentOffset += 4;
        long classId = ByteUtils.toUInt32(buffer);
        if(classId > mNdfbin.classes.size())
            throw new InvalidObjectException("This instance has... no class. Yeeeeeeaaaah...)");

        thisInstance.thisClass = mNdfbin.classes.get((int) classId);
        mNdfbin.classes.get((int) classId).instances.add(thisInstance);

        mReader.read(buffer);
        currentOffset += 4;
        long propertyId = ByteUtils.toUInt32(buffer);
        NdfbinClass cls = getClassFromId(classId);
        //READ PROPERTIES UNTIL END OF OBJECT
        while(propertyId != 2880154539L){     // == 0xABABABAB
            PropertyValue propval = new PropertyValue();
            propval.instance = thisInstance;

            propval.property = cls.getPropertyOfId(propertyId);

            if(propval.property != null){

                try {
                    propval.value = readValue();
                    if(mDebug){
                        try{
                            FlatValueWrapper wrapper = (FlatValueWrapper)(propval.value);
                            System.out.println("New Value : " + propval.property.name +
                                    " = " + wrapper.value + ", of class : " + propval.instance.thisClass.name + "("+propval.instance.Id+") ");
                        } catch (Exception e){
                            System.out.println("New Value : " + propval.property.name +" of class : "
                                    + propval.instance.thisClass.name + "("+propval.instance.Id+") "
                                    + " in't a flat value.");
                        }
                    }// PROBLEM
                } catch (Exception e) {
                    System.err.println("\nERROR : reading value of prop : " + propval.property.name +"(" + propval.property.Id +")"
                            + "of class " + propval.instance.thisClass.name + "("+propval.instance.Id+ ") "
                            + "at offset : " + currentOffset +"\n" + e.toString());
                    return null;
                }

                thisInstance.propertyValues.add(propval);
                buffer = new byte[4];
                mReader.read(buffer);
                currentOffset += 4;
                propertyId = ByteUtils.toUInt32(buffer);
            } else {
                System.err.println("\nNo property of this index : " + propertyId + " for this instance of class : "
                        + getClassFromId(thisInstance.Id).name + " at offset " + currentOffset + "\n");
                return null;
            }
        }

        return thisInstance;


    }

    private NdfbinValueWrapper readValue() throws Exception {
        long bufferLenght;
        NdfbinValueWrapper value;
        buffer = new byte[4];
        mReader.read(buffer);
        currentOffset += 4;
        NdfType type = NdfTypeManager.getTypeFromBytes(buffer);

        // GET DATA TYPE
        if(type == NdfType.UNKNOWN)
            throw new Exception("Unknown data type !!!!!");

        if(type == NdfType.REFERENCE){
            mReader.read(buffer);
            currentOffset += 4;
            type = NdfTypeManager.getTypeFromBytes(buffer);

        }

        //GET DATA SIZE
        switch (type){
            case WIDE_STRING:
            case LIST:
            case MAP_LIST:
            case BLOB:
            case ZIP_BLOB:
                mReader.read(buffer);
                currentOffset += 4;
                bufferLenght = ByteUtils.toUInt32(buffer);
                if(type == NdfType.ZIP_BLOB){
                    buffer = new byte[1];
                    currentOffset += 1;
                    if(mReader.read(buffer) != 1)
                        throw new Exception("Has to be checked, no idea.");
                }
                break;
            default:
                bufferLenght = NdfTypeManager.getSizeOf(type);
                break;
        }


        // GET DATA WRAPPER
        switch (type){
           case MAP_LIST:
           case LIST:
               NdfCollection list = type == NdfType.LIST ? new NdfCollection() : new NdfMapList();
               for(int i = 0; i<bufferLenght; i++){
                   CollectionItemValueHolder valueHolder;
                   if(type == NdfType.LIST){
                       valueHolder = new CollectionItemValueHolder(readValue(), mNdfbin);
                   } else {
                       valueHolder = new CollectionItemValueHolder(
                                        new NdfMap(
                                       new MapValueHolder(readValue(), mNdfbin),
                                       new MapValueHolder(readValue(), mNdfbin),
                                                mNdfbin), mNdfbin);
                   }
                   list.add(valueHolder);
               }
               value = list;
               break;

           case MAP:
                value =new NdfMap(  new MapValueHolder(readValue(), mNdfbin),
                                    new MapValueHolder(readValue(), mNdfbin), mNdfbin);
                break;

           default:
               buffer = new byte[(int) bufferLenght];
               mReader.read(buffer);
               currentOffset += bufferLenght;
               value = NdfTypeManager.getValue(buffer, type, mNdfbin);
               break;
        }

        return value;

    }
}
