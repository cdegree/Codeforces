package algorithms;

public class BitUtil {
    public static int countBit(long mask, int n) {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (testBit(mask, i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static long mergeBit(long mask, int pos) {
        return mask | (1L << pos);
    }

    public static boolean testBit(long mask, int pos) {
        return (mask & (1L << pos)) != 0;
    }
}
