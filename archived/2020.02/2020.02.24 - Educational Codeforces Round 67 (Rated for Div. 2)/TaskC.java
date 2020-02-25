package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Vector<Segment> order = new Vector<>();
        Vector<Segment> nonOrder = new Vector<>();
        for (int i = 0; i < m; ++i) {
            int t = in.nextInt();
            int l = in.nextInt();
            int r = in.nextInt();
            if (t == 1) {
                order.add(new Segment(l, r));
            } else {
                nonOrder.add(new Segment(l, r));
            }
        }
        Collections.sort(order, Comparator.comparingInt(x -> x.l));
        Vector<Segment> mergedOrder = new Vector<>();
        for (int i = 0; i < order.size(); ) {
            int right = order.get(i).r;
            int left = order.get(i).l;
            int j = i + 1;
            while (j < order.size() && order.get(j).l <= right) {
                right = Math.max(right, order.get(j).r);
                ++j;
            }
            i = j;
            mergedOrder.add(new Segment(left, right));
            //System.out.println(left+" -> "+right);
        }

        boolean illegal = false;
        for (Segment mergedorder : mergedOrder) {
            illegal = false;
            for (Segment nonorder : nonOrder) {
                if (mergedorder.l <= nonorder.l && nonorder.r <= mergedorder.r) {
                    illegal = true;
                    break;
                }
            }
            if (illegal) {
                break;
            }
        }
        Collections.sort(mergedOrder, Comparator.comparingInt(x -> x.l));
        if (!illegal) {
            out.println("YES");
            int k = 0;
            int[] a = new int[n];
            int cur = 1000000000;
            for (int i = 0; i < n; ) {
                int j = i;
                if (k < mergedOrder.size()) {
                    int orderLeft = mergedOrder.get(k).l;
                    int orderRight = mergedOrder.get(k).r;
                    if (orderLeft <= j + 1 && j + 1 <= orderRight) {
                        while (j < n && orderLeft <= j + 1 && j + 1 <= orderRight) {
                            a[j] = cur;
                            ++j;
                        }
                        ++k;
                        --cur;
                    } else {
                        a[j++] = cur;
                        --cur;
                    }
                } else {
                    a[j++] = cur;
                    --cur;
                }
                i = j;

            }
            for (int i = 0; i < n; ++i) {
                out.print(a[i] + " ");
            }
        } else {
            out.println("NO");
        }
    }

    class Segment {
        int l, r;

        public Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
