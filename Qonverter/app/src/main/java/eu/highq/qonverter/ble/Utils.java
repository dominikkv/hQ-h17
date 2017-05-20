package eu.highq.qonverter.ble;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils {

    private static final int MDER_POSITIVE_INFINITY = 0x007FFFFE;
    private static final int MDER_NaN = 0x007FFFFF;
    private static final int MDER_NRes = 0x00800000;
    private static final int MDER_RESERVED_VALUE = 0x00800001;
    private static final int MDER_NEGATIVE_INFINITY = 0x00800002;

    private static final int FIRST_RESERVED_VALUE = MDER_POSITIVE_INFINITY;
    private static final double[] reserved_float_values = {Double.POSITIVE_INFINITY, Double.NaN, Double.NaN, Double.NaN, Double.NEGATIVE_INFINITY};


    public static double float_from_IEEE11073(ByteBuffer input) {
        byte[] val = new byte[4];
        input.get(val, 0, 4);

        return Utils.float_from_IEEE11073(val);
    }

    /**
     * Consumes an intu32 from data, and calculates the float as described in MDER Annex F.6.
     *
     * @param input The current bytes.
     * @return float Converted float from stream.
     */
    public static double float_from_IEEE11073(byte[] input) {
        int int_data = ByteBuffer.wrap(input).order(ByteOrder.nativeOrder()).getInt(0);

        int mantissa = int_data & 0xFFFFFF;
        int exponent = int_data >> 24;

        double output;

        if ((mantissa >= FIRST_RESERVED_VALUE) && (mantissa <= MDER_NEGATIVE_INFINITY)) {
            output = reserved_float_values[mantissa - FIRST_RESERVED_VALUE];
        } else {
            if (mantissa >= 0x800000) {
                mantissa = -((0xFFFFFF + 1) - mantissa);
            }
            output = (mantissa * Math.pow(10.0f, exponent));
        }

        return output;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 3 - 1];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 3] = hexArray[v >>> 4];
            hexChars[j * 3 + 1] = hexArray[v & 0x0F];

            if (j < bytes.length - 1) {
                hexChars[j * 3 + 2] = '-';
            }
        }
        return new String(hexChars);
    }
}
