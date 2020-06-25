package algorithms;

import java.util.Arrays;
import java.util.TreeMap;

public class Utils {
    public static String YESNO(boolean OK) {
        return OK ? "YES" : "NO";
    }

    public class Pair implements Comparable<Pair> {
        int x;
        int y;
        @Override
        public int compareTo(Pair o) {
            if (o.x == x) return y - o.y;
            return x - o.x;
        }
    }


}
