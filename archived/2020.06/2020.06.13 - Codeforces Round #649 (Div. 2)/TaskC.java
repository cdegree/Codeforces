package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        LinkedList<Integer> missValues = new LinkedList<>();
        int v = 0;
        int j = 0;
        for (int i = 0; i <= n + n; ++i) {
            if (j < n && i < a[j]) {
                missValues.add(i);
            } else if (j < n && i > a[j]) {
                missValues.add(i);
            } else if (j >= n) {
                missValues.add(n + 1);
            }
            while (j < n && i == a[j]) {
                ++j;
            }
        }
//        out.println("size= "+missValues.size());
//        for(int x:missValues){
//            out.println(x);
//        }
        int[] b = new int[n];
        boolean[] used = new boolean[1000005];
        int s = 0;
        j = 0;
        while (j < n) {
            if (s == a[j]) {
                b[j] = missValues.removeFirst();
                used[b[j]] = true;
                while(used[s])++s;
            } else {
                b[j] = s;
                used[s] = true;
                ++s;
                while(used[s])++s;
                while (!missValues.isEmpty() && missValues.peek() < s) {
                    missValues.removeFirst();
                }
            }
            ++j;
        }
        for (int i = 0; i < n; ++i) {
            out.print(b[i] + " ");
        }
    }
}
