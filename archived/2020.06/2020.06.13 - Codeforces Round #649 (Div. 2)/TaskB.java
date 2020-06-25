package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int first = a[0];
            int second = a[1];
            LinkedList<Integer> res = new LinkedList<>();
            res.add(first);
            res.add(second);
            for (int i = 2; i < n; ++i) {
                if ((first < second && second < a[i]) || (first > second && second > a[i])) {
                    second = a[i];
                    res.removeLast();
                    res.add(second);
                } else {
                    first = second;
                    second = a[i];
                    res.add(a[i]);
                }
            }
            out.println(res.size());
            for (int x : res) {
                out.print(x + " ");
            }
        }
    }
}
