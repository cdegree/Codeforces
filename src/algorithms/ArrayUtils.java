package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ArrayUtils {
    public static int[] sortAndUnique(int[] a) {
        Arrays.sort(a);
        int equalCount = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] == a[i - 1]) {
                equalCount++;
            }
        }
        int[] b = new int[a.length - equalCount];
        int last = a[0] - 1;
        int j = 0;
        for (int i = 0; i < a.length; ++i) {
            if (last != a[i]) {
                b[j++] = a[i];
                last = a[i];
            }
        }
        return b;
    }

    public static int[] shrink(int[] a) {
        int n = a.length;
        int[] ret = new int[n];
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; ++i) {
            b[i] = i;
        }
        Arrays.sort(b, Comparator.comparingInt(x -> a[x]));
        int p = 0;
        for (int i = 0; i < n; ++i) {
            if (i - 1 >= 0 && a[b[i]] == a[b[i - 1]]) {
                ret[b[i]] = p;
            } else {
                ret[b[i]] = ++p;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] a = {9923, -2, -2, 5, -8, 23, -493, 6, -2, 78, 10000};
        int[] b = {8, 3, 3, 4, 2, 6, 1, 5, 3, 7, 9};
        a = shrink(a);
        for (int i = 0; i < a.length; ++i) {
            System.out.println(a[i] + " " + b[i]);
        }
    }
}
