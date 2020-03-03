package algorithms;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MathUtil {
    public static int INF = 1 << 31;
    public static long LINF = 1L << 61;


    public static Vector<Integer> getDivisors(int n) {
        Vector<Integer> r = new Vector<>();
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                r.add(i);
                if (n / i != i) {
                    r.add((n / i));
                }
            }
        }

        return r;
    }

    /**
     * Time complexity sqrt(n)
     *
     * @param n
     * @return all unique divisors of n
     */
    public static Vector<Long> getDivisors(long n) {
        Vector<Long> r = new Vector<>();
        for (Long i = 1L; i * i <= n; ++i) {
            if (n % i == 0) {
                r.add(i);
                if (n / i != i) {
                    r.add((n / i));
                }
            }
        }
        return r;
    }

    public static Map<Long, Integer> getPrimeDivisors(long n) {
        Map<Long, Integer> r = new TreeMap<>();
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                r.put(i, 0);
                int cnt = 0;
                while (n % i == 0) {
                    ++cnt;
                    n /= i;
                }
                r.put(i, cnt);
            }
        }
        if (n > 1) {
            r.put(n, 1);
        }
        return r;
    }

    public static long getGCD(long a, long b) {
        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        if (b == 0) {
            return a;
        } else {
            return getGCD(b, a % b);
        }
    }

    public static long modPow(long a, long b, long mod) {
        long ret = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                ret = (ret * a) % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return ret;
    }

    public static long getInv(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    // Count the number of gcd(0,m)=m>1 where x∈[1,m−1] and this is the definition of Euler's totient function φ(m′) which is the answer.
    //
    //Euler's totient function φ(m) can be calculated using factorization of m = ∏ pi^ai . Then φ(m) = m * ∏ (1-1/pi).
    public static long getEulerTotientFunction(long x) {
        Map<Long, Integer> primes = getPrimeDivisors(x);
        if (x == 1) {
            return 1;
        }
        long ret = x;
        //System.out.println("x = " + x);
        for (long prime : primes.keySet()) {
            //System.out.println("prime = " + prime);
            ret = ret / prime * (prime - 1);
        }
        //System.out.println("ret = " + ret);
        return ret;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; ++i) {
            System.out.print(getEulerTotientFunction(i) + " ");
            if (i % 10 == 0) {
                System.out.println();
            }
        }
    }
}
