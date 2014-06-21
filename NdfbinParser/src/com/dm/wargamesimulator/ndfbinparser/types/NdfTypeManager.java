package com.dm.wargamesimulator.ndfbinparser.types;

import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinClass;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinStringRef;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.ByteUtils;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.*;

/**
 * Created by inv on 20/05/14.
 */
public class NdfTypeManager {



    public static NdfType getTypeFromBytes(byte[] dataType){

        if (dataType.length != 4)
            return NdfType.UNKNOWN;

        long value = ByteUtils.toUInt32(dataType);

        return NdfType.getTypeFromValue(value);
    }


    public static NdfbinValueWrapper getValue(byte[]data, NdfType type, Ndfbin ndfbin){
        switch (type){

            case BOOLEAN:
                return new NdfBoolean(ByteUtils.toBoolean(data[0]));
            case INT8:
                return new NdfInt8(data[0]);
            case UINT16:
                byte[] newByte = {data[0], data[1], 0, 0};
                return  new NdfUInt16(ByteUtils.toUInt32(newByte));
            case INT16:
                return new NdfInt16(ByteUtils.toInt16(data));

            case INT32:
                return new NdfInt32(ByteUtils.toUInt32(data));
            case UINT32:
                return new NdfUInt32(ByteUtils.toUInt32(data));
            case FLOAT32:
                return new NdfSingle(ByteUtils.toFloat32(data));
            case TABLE_STRING:
                NdfbinStringRef ref =  new NdfbinStringRef();
                ref.Id = ByteUtils.toUInt32(data);
                return new NdfString(ndfbin.strings.get((int)ref.Id));

            case TABLE_STRING_FILE:
                NdfbinStringRef file =  new NdfbinStringRef();
                file.Id = ByteUtils.toUInt32(data);
                return new NdfFileNameString(ndfbin.strings.get((int)file.Id));
            case COLOR32:
                return new NdfColor32(data[0], data[1], data[2], data[3]);

            case WIDE_STRING:
                return new NdfWideString(ByteUtils.toString(data));

            case LOCALISATION_HASH:
                return new NdfLocalisationHash(data);


            case OBJECT_REFERENCE:
                byte[] clsBytes = {data[4], data[5], data[6], data[7]};
                long cls = ByteUtils.toUInt32(clsBytes);
                byte[] instByte = {data[0], data[1], data[2], data[3]};
                long inst = ByteUtils.toUInt32(instByte);

                NdfbinClass ndfClass = new NdfbinClass();
                if(cls <= ndfbin.classes.size())
                    ndfClass = ndfbin.classes.get((int)cls);
                return new NdfObjectReference(ndfClass, inst);

            case EUGINT2:
                byte[] val1 = {data[0], data[1], data[2], data[3]};
                byte[] val2 = {data[4], data[5], data[6], data[7]};
                return new NdfEugInt2(ByteUtils.toInt(val1), ByteUtils.toInt(val2) );
            case TIME64:
                return new NdfTime64(data);

            case TRIPPLE_INT:
                byte[] tri1 = {data[0], data[1], data[2], data[3]};
                byte[] tri2 = {data[4], data[5], data[6], data[7]};
                byte[] tri3 = {data[8], data[9], data[10], data[11]};
                return new NdfTripleInt(ByteUtils.toInt(tri1), ByteUtils.toInt(tri2),ByteUtils.toInt(tri3));

            case VECTOR:
                byte[] v1 = {data[0], data[1], data[2], data[3]};
                byte[] v2 = {data[4], data[5], data[6], data[7]};
                byte[] v3 = {data[8], data[9], data[10], data[11]};
                return new NdfVector(ByteUtils.toFloat32(v1), ByteUtils.toFloat32(v2),ByteUtils.toFloat32(v3));


            case COLOR128:
                return new NdfColor128(data);
            case GUID:
                return new NdfGuid(data);
            case HASH:
                return new NdfHash(data);


            case MAP:
                return new NdfMap(new MapValueHolder(null, ndfbin), new MapValueHolder(null, ndfbin), ndfbin);

            case LIST:
                return new NdfCollection();
            case MAP_LIST:
                return new NdfMapList();

            case FLOAT64:
                return new NdfDouble(ByteUtils.toFloat64(data));

            case FLOAT32_2:
                byte[] float1 = {data[0], data[1], data[2], data[3]};
                byte[] float2 = {data[4], data[5], data[6], data[7]};
                return new NdfFloat32_2(ByteUtils.toFloat32(float1), ByteUtils.toFloat32(float2));

            case TRANS_TABLE_REFERENCE:
                long transIndex = ByteUtils.toUInt32(data);
                return new NdfTrans(ndfbin.trans.get((int) transIndex));

            case BLOB:
                return new NdfBlob(data);
            case ZIP_BLOB:
                return new NdfZipBlob(data);

            case UNSET:
                return new NdfNull();

            default:
                return null;

        }
    }

    public static int getSizeOf(NdfType type){
        switch (type){

            case BOOLEAN:
            case INT8:
                return 1;

            case UINT16:
            case INT16:
                return 2;


            case INT32:
            case UINT32:
            case FLOAT32:
            case TABLE_STRING:
            case TABLE_STRING_FILE:
            case COLOR32:
            case WIDE_STRING:
                return 4;

            case LOCALISATION_HASH:
            case OBJECT_REFERENCE:
            case EUGINT2:
            case TIME64:
                return 8;

            case TRIPPLE_INT:
            case VECTOR:
                return 12;


            case COLOR128:
            case GUID:
            case HASH:
                return 16;


            case MAP:
                return 0;

            case LIST:
            case MAP_LIST:
                return 4;

            case FLOAT64:
            case FLOAT32_2:
                return 8;


            case TRANS_TABLE_REFERENCE:
                return 4;

            default:
                return 0;

        }
    }

    public static NdfType[] getTypeSelection(){
        NdfType[] result = {
                    NdfType.BOOLEAN,
                    NdfType.INT32,
                    NdfType.UINT32,
                    NdfType.FLOAT32,
                    NdfType.OBJECT_REFERENCE,
                    NdfType.MAP,
                    NdfType.LIST,
                    NdfType.TABLE_STRING,
                    NdfType.TABLE_STRING_FILE,
                    NdfType.LOCALISATION_HASH,
                    NdfType.WIDE_STRING,
                    NdfType.TRANS_TABLE_REFERENCE,
                    NdfType.GUID,
                    NdfType.INT8,
                    NdfType.INT16,
                    NdfType.UINT16,
                    NdfType.COLOR32,
                    NdfType.FLOAT64,
                    NdfType.FLOAT32_2,
                    NdfType.VECTOR,
                    NdfType.COLOR128,
                    NdfType.MAP_LIST
        };
        return result;
    }

    public static Class getWrapperClassFromType(NdfType type){
        switch(type){
            case BLOB:
                return NdfBlob.class;
            case ZIP_BLOB:
                return NdfZipBlob.class;
            case BOOLEAN:
                return NdfBoolean.class;
            case INT32:
                return NdfInt32.class;
            case INT16:
                return NdfInt16.class;
            case INT8:
                return NdfInt8.class;
            case UINT32:
                return NdfUInt32.class;
            case UINT16:
                return NdfUInt16.class;
            case FLOAT64:
                return NdfDouble.class;
            case FLOAT32:
                return  NdfSingle.class;
            case FLOAT32_2:
                return NdfFloat32_2.class;
            case OBJECT_REFERENCE:
                return NdfObjectReference.class;
            case MAP:
                return NdfMap.class;
            case MAP_LIST:
                return NdfMapList.class;
            case LIST:
                return NdfCollection.class;
            case TABLE_STRING:
                return NdfString.class;
            case TABLE_STRING_FILE:
                return NdfFileNameString.class;
            case TRANS_TABLE_REFERENCE:
                return NdfTrans.class;
            case LOCALISATION_HASH:
                return NdfLocalisationHash.class;
            case HASH:
                return NdfHash.class;
            case WIDE_STRING:
                return NdfWideString.class;
            case GUID:
                return NdfGuid.class;
            case COLOR32:
                return NdfColor32.class;
            case COLOR128:
                return NdfColor128.class;
            case VECTOR:
                return NdfVector.class;
            case TRIPPLE_INT:
                return NdfTripleInt.class;


            default:
                return null;
        }
    }
}
