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
            int[] a = in.nextIntArray(n + n);
            LinkedList<Integer> odd = new LinkedList<>();
            LinkedList<Integer> even = new LinkedList<>();
            for (int i=0;i<2*n;++i) {
                if (a[i] % 2 == 0) {
                    even.add(i+1);
                } else {
                    odd.add(i+1);
                }
            }
            Vector<Integer> res = new Vector<>();
            for (int i = 0; i < n - 1; ++i) {
                if (even.size() >= 2) {
                    res.add(even.removeLast());
                    res.add(even.removeLast());
                } else if (odd.size() >= 2) {
                    res.add(odd.removeLast());
                    res.add(odd.removeLast());
                }
            }
            int cnt=0;
            for (int i = 0; i < n - 1; i ++) {
                out.println(res.get(cnt) + " " + res.get(cnt + 1));
                cnt+=2;
            }
        }
    }
}
