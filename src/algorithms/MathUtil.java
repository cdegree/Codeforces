package algorithms;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MathUtil {

    public static Vector<Integer> getDivisors(int n) {
        Vector<Integer> r = new Vector<>();
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                r.add(i);
                if (n / i != i) {
                    r.add( (n / i));
                }
            }
        }
        return r;
    }

    public static Map<Integer, Integer> getPrimeDivisors(long n) {
        Map<Integer, Integer> r = new TreeMap<>();
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                r.put((int) i, 0);
                while (n % i == 0) {
                    r.put((int) i, r.get(i) + 1);
                    n /= i;
                }
            }
        }
        if (n > 1) {
            r.put((int) n, 1);
        }
        return r;
    }
}
