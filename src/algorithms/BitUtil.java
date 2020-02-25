package algorithms;

public class BitUtil {
    public int countBit(int mask, int n) {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (testBit(mask, i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public int mergeBit(int mask, int pos) {
        return mask | (1 << pos);
    }

    public boolean testBit(int mask, int pos) {
        return (mask & (1 << pos)) != 0;
    }
}
