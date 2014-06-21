package com.dm.wargamesimulator.ndfbinparser.ndfbin;

import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;
import com.google.common.primitives.UnsignedLong;
import com.sun.deploy.util.ArrayUtil;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by inv on 13/05/14.
 */
public class ByteUtils {


    public static boolean equals(byte[] array1, byte[] array2){

        if(array1.length == 0 || array2.length == 0)
            return false;

        if(array1.length != array2.length){
            return false;
        }


        int count = array1.length;

        for(int i = 0; i < count; i ++){
            if(array1[0] != array2[0])
                return false;
        }

        return true;
    }


    public static String toString(byte[] array){

        int count = array.length;
        if(count == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i ++){
            sb.append((char)array[i]);
        }

        return sb.toString();

    }

    public static long toUInt32(byte[] array){
        int count = array.length;
        if(count > 4)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return UnsignedInts.toLong(wrapped.getInt());

    }

    public static short toInt16(byte[] array){
        int count = array.length;
        if(count > 2)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getShort();

    }

    public static long toLong(byte[] array){
        int count = array.length;
        if(count > 8)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getLong();
    }

    public static long toLongNotReversed(byte[] array){
        int count = array.length;
        if(count > 8)
            throw new IllegalArgumentException("Byte array argument is too large");
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getLong();
    }


    public static int toInt(byte[] array){
        int count = array.length;
        if(count > 4)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getInt();
    }

    public static float toFloat32(byte[] array){
        int count = array.length;
        if(count > 4)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getFloat();
    }

    public static UnsignedLong toULong(byte[] array){
        long signedResult = toLong(array);
        return UnsignedLong.fromLongBits(signedResult);
    }

    public static double toFloat64(byte[] array){
        int count = array.length;
        if(count > 8)
            throw new IllegalArgumentException("Byte array argument is too large");
        reverse(array);
        ByteBuffer wrapped = ByteBuffer.wrap(array);

        return wrapped.getDouble();
    }



    public static void reverse(byte[] buffer) {

        if(buffer == null)
            return ;

        int count = buffer.length;

        int i = 0;
        int j = count - 1;
        byte tmp;
        while (j > i) {
            tmp = buffer[j];
            buffer[j] = buffer[i];
            buffer[i] = tmp;
            j--;
            i++;
        }
    }


    public static boolean toBoolean(byte buffer){
        if(buffer == 0x0){
            return false;
        } else {
            return true;
        }
    }

    public static int toInt8(byte buf){
        return (int)byte2short((byte)0x0, buf);
    }

    private static short byte2short(final byte left, final byte right) {
        return (short) ((left & 0xff) << 8 | right & 0xff);
    }
}
