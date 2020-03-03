package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ArrayUtils {

    public static void reverse(int[] a) {
        int temp;
        for (int i = 0; i < a.length / 2; i++) {
            temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
    }

    public static void reverse(long[] a) {
        long temp;
        for (int i = 0; i < a.length / 2; i++) {
            temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
    }

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

    public static long[] sortAndUnique(long[] a) {
        Arrays.sort(a);
        int equalCount = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] == a[i - 1]) {
                equalCount++;
            }
        }
        long[] b = new long[a.length - equalCount];
        long last = a[0] - 1;
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

        long[] c = {5, 32, 2, 3, 1903, 4, 23, 4, 56, 3, 65, 367, 8, 5, 45, 45363, 656, 23};
        mergeSort(c);
        for (long v : c) {
            System.out.println(v);
        }
    }

    public static int minElement(int[] a, int start, int end) {
        int ret = a[start];
        for (int i = start; i < end; ++i) {
            ret = Math.min(ret, a[i]);
        }
        return ret;
    }

    public static int maxElement(int[] a, int start, int end) {
        int ret = a[start];
        for (int i = start; i < end; ++i) {
            ret = Math.max(ret, a[i]);
        }
        return ret;
    }

    public static long minElement(long[] a, int start, int end) {
        long ret = a[start];
        for (int i = start; i < end; ++i) {
            ret = Math.min(ret, a[i]);
        }
        return ret;
    }

    public static long maxElement(long[] a, int start, int end) {
        long ret = a[start];
        for (int i = start; i < end; ++i) {
            ret = Math.max(ret, a[i]);
        }
        return ret;
    }

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l + 1 < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(a, l, m);
            mergeSort(a, m, r);

            // Merge the sorted halves
            merge(a, l, m, r);
        }
    }

    public static void merge(long arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l;
        int n2 = r - m;

        /* Create temp arrays */
        long L[] = new long[n1];
        long R[] = new long[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(long[] a) {
        mergeSort(a, 0, a.length);
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void mergeSort(long[] a, int l, int r) {
        if (l + 1 < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(a, l, m);
            mergeSort(a, m, r);

            // Merge the sorted halves
            merge(a, l, m, r);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + j];

        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
