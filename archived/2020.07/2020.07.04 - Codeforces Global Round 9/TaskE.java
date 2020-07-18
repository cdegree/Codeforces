package current;

import algorithms.Pair;
import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Vector;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Vector<Pair> p = new Vector<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] > a[j]) {
                    p.add(new Pair(i, j));
                }
            }
        }
        p.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.y != o2.y) {
                    return o2.y - o1.y;
                } else {
                    return a[o1.x] - a[o2.x];
                }
            }
        });
//        for (int i = 0; i < p.size(); ++i) {
//            int t = a[p.get(i).x];
//            a[p.get(i).x] = a[p.get(i).y];
//            a[p.get(i).y] = t;
//            //System.out.println(p.get(i).x + " " + p.get(i).y);
//        }
//        boolean OK = true;
//        for (int i = 1; i < n; ++i) {
//            if (a[i] < a[i - 1]) {
//                OK = false;
//            }
//        }
//        for (int i = 0; i < n; ++i) {
//            System.out.print(a[i] + " ");
//        }
//        out.println();
        out.println(p.size());
        for (Pair x : p) {
            out.println((x.x + 1) + " " + (x.y + 1));
        }
    }
}
