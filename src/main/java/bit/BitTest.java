package bit;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.concurrent.ForkJoinTask;

/**
 * @author douzhitong
 * @date 2020/6/17
 *
 * 结论：
 * 1、二进制异或运算的正负取决于两个数的正负，值和符号分别做异或运算（最高位就是符号位） -- testMSB
 * 2、异或的运算规则是半加法，即不进位的加法
 */
public class BitTest {

    private static final long SP_MASK    = 0xffffffffL;
    private static final long UC_MASK    = ~SP_MASK;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        testBit();
        testMSB();
//        testUnsafe();
//        testProtobufShift();
    }

    private static void testBit() {
        // java所有数值都是用补码存储的
        // 正数的补码 = 源码
        // 负数的补码 = 原码除符号位外的所有位取反后加1
//        System.out.println(SP_MASK);
//        System.out.println(UC_MASK);
//        System.out.println(Long.toBinaryString(4294967296L));
//        System.out.println(Long.toBinaryString(SP_MASK));
//        System.out.println(Long.toBinaryString(UC_MASK));
//        System.out.println(Long.toBinaryString(-4L));
//        System.out.println(Long.toBinaryString(-4L << 48));
//        System.out.println(Long.toBinaryString(-4L << 32));
//        System.out.println(Long.toBinaryString((-4L << 48) | (-4L << 32)));
//        System.out.println(Long.toBinaryString((1 << 13) >>> 1));
//        System.out.println(-1L >>> 2);
//        System.out.println(-1L >> 2);
//        System.out.println( Long.toBinaryString(1));
//        System.out.println( Long.toBinaryString(-1));
//        System.out.println( 1 << 1);
//        System.out.println( -1 << 1);
//        System.out.println( Long.toBinaryString(1 << 1));
//        System.out.println( Long.toBinaryString(1 >> 31));
//        System.out.println( Long.toBinaryString((1 << 1) ^ (1 >> 31)));
//        System.out.println( Long.toBinaryString(-1 << 1));
//        System.out.println( Long.toBinaryString(-1 >> 31));
//        System.out.println( Long.toBinaryString((-1 << 1) ^ (-1 >> 31)));
//        System.out.println(bit2byte("01010101"));
//        System.out.println(bit2byte("01010101") << 7);
//        System.out.println(Long.toBinaryString(bit2byte("01010101")));
//        System.out.println(Long.toBinaryString(bit2byte("01010101")  << 7));
//        System.out.println(bit2byte("01010101") ^ (bit2byte("01010101")  << 7) ^ (~0 << 7));
//        System.out.println(Long.toBinaryString(bit2byte("01010101") ^ (bit2byte("01010101")  << 7) ^ (~0 << 7)));
//        System.out.println(Long.toBinaryString(0 << 7));
//        System.out.println(Long.toBinaryString(1 << 7));
    }

    // 二进制的正负取决于最高一位的正负
    public static void testMSB() {
//        System.out.println(bit2byte("01010101") ^ (bit2byte("01010101")  << 7));
//        System.out.println(Long.toBinaryString(bit2byte("01010101") ^ (bit2byte("01010101")  << 7)));
        System.out.println(bit2byte("01010101") ^ (bit2byte("11010101")  << 7));
        System.out.println(Long.toBinaryString(bit2byte("01010101") ^ (bit2byte("11010101")  << 7)));
//        System.out.println(bit2byte("11010101") ^ (bit2byte("01010100")  << 7));
//        System.out.println(Long.toBinaryString(bit2byte("11010101") ^ (bit2byte("01010100")  << 7)));
//        System.out.println(bit2byte("11010101") ^ (bit2byte("11010101")  << 7));
//        System.out.println(Long.toBinaryString(bit2byte("11010101") ^ (bit2byte("11010101")  << 7)));
        System.out.println(bit2byte("01010101") ^ (bit2byte("11010101")  << 7) ^ (~0 << 7));
        System.out.println(Long.toBinaryString(bit2byte("01010101") ^ (bit2byte("11010101")  << 7) ^ (~0 << 7)));
    }

    // protobuf读取数据的档位
    public static void testProtobufShift() {
        System.out.println(Long.toBinaryString(~0));
        System.out.println(Long.toBinaryString(~0 << 7));
        System.out.println(Long.toBinaryString((~0 << 7) ^ (~0 << 14)));
        System.out.println(Long.toBinaryString((~0 << 7) ^ (~0 << 14) ^ (~0 << 21)));
        System.out.println(Long.toBinaryString((~0 << 7) ^ (~0 << 14) ^ (~0 << 21) ^ (~0 << 28)));
    }

    // 二进制字符串转Byte
    public static byte bit2byte(String bString){
        byte result=0;
        for(int i=bString.length()-1,j=0;i>=0;i--,j++){
            result+=(Byte.parseByte(bString.charAt(i)+"")*Math.pow(2, j));
        }
        return result;
    }

    private static void testUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        getUnsafe.setAccessible(true);
        Unsafe U = (Unsafe) getUnsafe.get(null);
        Class<?> ak = ForkJoinTask[].class;
        int scale = U.arrayIndexScale(ak);
        System.out.println(scale);
    }
}
