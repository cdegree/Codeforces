package current;

import fastio.InputReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class TaskC {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        Line W = new Line(w);
        Line H = new Line(h);
        for (int i = 0; i < n; ++i) {
            char s = in.next().charAt(0);
            int x = in.nextInt();
            if (s == 'H') {
                H.cut(x);
            } else {
                W.cut(x);
            }
            out.println(H.getLargest() * W.getLargest());
        }
    }

    class Line {
        int n;
        TreeSet<Integer> cuts = new TreeSet<>();
        TreeSet<Integer> segCount = new TreeSet<>();
        int[] xcnt = null;

        Line(int n) {
            this.n = n;
            cuts.add(0);
            cuts.add(n);
            segCount.add(n);
            xcnt = new int[n + 1];
            xcnt[n]++;
        }

        void cut(int x) {
            int lowerCut = cuts.lower(x);
            int higherCut = cuts.higher(x);
            cuts.add(x);
            int nLcut = x - lowerCut;
            int nHcut = higherCut - x;
            int diff = higherCut - lowerCut;
            if (--xcnt[diff] == 0) {
                segCount.remove(diff);
            }
            if(xcnt[nLcut]++==0) {
                segCount.add(nLcut);
            }
            if(xcnt[nHcut]++==0) {
                segCount.add(nHcut);
            }
        }
        long getLargest() {
            return segCount.last();
        }
    }
}
