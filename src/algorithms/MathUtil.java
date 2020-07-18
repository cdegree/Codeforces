package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MathUtil {
    public static int INF = 1 << 31;
    public static long LINF = 1L << 61;

    public static void main(String[] args) throws IOException {
//        for (int i = 1; i <= 100; ++i) {
//            //System.out.print(getEulerTotientFunction(i) + " ");
//            if (i % 10 == 0) {
//                //System.out.println();
//            }
//        }
//        int n = 20;
//
//        while (n != 0) {
//            //System.out.println(n%-2);
//            n /= -2;
//        }
//        int e = 1;
//        for (int i = 0; i < 10; ++i) {
//            //System.out.println(e);
//            e *= -2;
//        }
//        Vector<Integer> res = new Vector<>();
//        TreeSet<Integer> set = new TreeSet<>();
//
//        TreeSet<Integer> nset = set;
//
//        nset.add(23);
//        System.out.println(set.size());
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        int m = 150;

        String line = "";
        while ((line = br.readLine())!=null) {
            int[] cnt = new int[m];
            for(char ch:line.toCharArray()){
                cnt[ch]++;
            }
            Vector<Integer> index = new Vector<>();
            for (int i = 0; i < m; ++i) {
                index.add(i);
            }
            index.sort((o1, o2) -> {
                if (cnt[o1] != cnt[o2]) return cnt[o2] - cnt[o1];
                return o1 - o2;
            });
            for (int i = 0; i < m; ++i) {
                if (cnt[index.get(i)] > 0) {
                    char ch = (char) index.get(i).intValue();
                    //if (Character.isLetter(ch) || Character.isDigit(ch) || Character.isSpaceChar(ch))
                        //System.out.println(ch+" "+cnt[index.get(i)]);
                    System.out.print(ch);
                }
            }
            System.out.println();
            //2 12
            //0 10
        }
    }

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

    public static TreeMap<Integer, Integer> getPrimeDivisors(int n) {
        TreeMap<Integer, Integer> r = new TreeMap<>();
        for (int i = 2; (long) i * i <= n; ++i) {
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

    public static TreeMap<Long, Integer> getPrimeDivisors(long n) {
        TreeMap<Long, Integer> r = new TreeMap<>();
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

    public static int getGCD(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        if (b == 0) {
            return a;
        } else {
            return getGCD(b, a % b);
        }
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


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ret = new LinkedList<>();

        TreeSet<List<Integer>> set = new TreeSet<>(new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                for (int i = 0; i < o1.size(); ++i) {
                    if (o1.get(i) != o2.get(i)) {
                        return o1.get(i) - o2.get(i);
                    }
                }
                return 0;
            }
        });

        solve(candidates, 0, target, new LinkedList<>(), set);

        System.out.println(set.size());
        for (List<Integer> list : set) {
            for (int a : list) {
                System.out.println(a);
            }
            ret.add(list);
        }

        return ret;
    }

    void solve(int[] candidates, int sum, int target, LinkedList<Integer> cur, TreeSet<List<Integer>> set) {

        //System.out.println(sum+" "+target+" "+cur.size());

        if (sum == target && !cur.isEmpty()) {

            System.out.print(" cur :");
            for (int a : cur) {
                System.out.print(a + " ");
            }
            System.out.println();
            set.add(new ArrayList<>(cur));
            System.out.println(set.size());
            char c = '3';
            Character d = 'a';

            return;
        }

        for (int i = 0; i < candidates.length; ++i) {
            if (sum + candidates[i] <= target) {
                cur.add(candidates[i]);
                solve(candidates, sum + candidates[i], target, cur, set);
                cur.removeLast();
            }
        }
    }


    public class RealNumber {

        int numerator;
        int denominator;

        public RealNumber(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            reduce();
        }

        public RealNumber(int x) {
            this.numerator = x;
            this.denominator = 1;
        }

        public void reduce() {
            int gcd = getGCD(this.numerator, this.denominator);
            if (gcd != 0) {
                this.numerator /= gcd;
                this.denominator /= gcd;
            }
        }

        public RealNumber substract(RealNumber y) {
            int numerator = this.numerator * y.denominator - y.numerator * this.denominator;
            int denominator = this.denominator * y.denominator;
            return new RealNumber(numerator, denominator);
        }

        public RealNumber plus(RealNumber y) {
            int numerator = this.numerator * y.denominator + y.numerator * this.denominator;
            int denominator = this.denominator * y.denominator;
            return new RealNumber(numerator, denominator);
        }

        public RealNumber divide(RealNumber y) {
            int numerator = this.numerator * y.denominator;
            int denominator = this.denominator * y.numerator;

            return new RealNumber(numerator, denominator);
        }

        public RealNumber multiply(RealNumber y) {
            int numerator = this.numerator * y.numerator;
            int denominator = this.denominator * y.denominator;
            return new RealNumber(numerator, denominator);
        }

        public boolean equals(RealNumber y) {
            reduce();
            y.reduce();
            return numerator == y.numerator && denominator == y.denominator;
        }

        int getGCD(int a, int b) {
            if (a < b) {
                int t = a;
                a = b;
                b = t;
            }
            if (b == 0) {
                return a;
            } else {
                return getGCD(b, a % b);
            }
        }
    }
}
