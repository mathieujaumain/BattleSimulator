package com.dm.wargamesimulator.ndfbinparser.types;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 02/05/14
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public enum NdfType {

    BOOLEAN(0x00000000),
    INT8(0x00000001),

    INT16(0x00000018),
    UINT16(0x00000019),

    INT32(0x00000002),
    UINT32(0x00000003),
    FLOAT32(0x00000005),
    FLOAT64(0x00000006),
    FLOAT32_2(33),

    TIME64(0x00000004),

    EUGINT2(0x0000001F),

    GUID(26),
    VECTOR(0x0000000b),
    COLOR128(0x0000000c),
    COLOR32(0x0000000d),
    TRIPPLE_INT(0x0000000e),

    TABLE_STRING(0x00000007),
    TABLE_STRING_FILE(0x0000001C),
    WIDE_STRING(0x00000008),

    LOCALISATION_HASH(29),

    HASH(0x00000025),

    REFERENCE(0x00000009),
    OBJECT_REFERENCE(0xBBBBBBBBL),
    TRANS_TABLE_REFERENCE(0xAAAAAAAAL),

    MAP(0x00000022),

    BLOB(0x00000014),
    ZIP_BLOB(0x0000001E),

    LIST(0x00000011),
    MAP_LIST(0x00000012),

    UNKNOWN(0xFFFFFFFFL),
    UNSET(0xEEEEEEEEL);


    private final long value;
    NdfType(long avalue) {
        this.value = avalue;
    }

    public static NdfType getTypeFromValue(long value){
        NdfType[] values = NdfType.values();
        int count = values.length;
        NdfType result = NdfType.UNKNOWN;
        for(int i = 0; i < count; i++){
            if(values[i].value == value){
                result = values[i];
                break;
            }
        }
       return  result;
    }
}
