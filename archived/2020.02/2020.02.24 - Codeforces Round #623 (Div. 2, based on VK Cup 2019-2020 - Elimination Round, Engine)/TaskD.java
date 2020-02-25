package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] t = in.nextIntArray(n);
        Category[] categories = new Category[n];
        for (int i = 0; i < n; ++i) {
            categories[i] = new Category(a[i], t[i]);
        }
        Arrays.sort(categories, Comparator.comparingLong(x -> x.a));
        long cost = 0;
        for (int i = 0; i < n; ) {
            long cur = categories[i].a;
            int j = i + 1;
            long psum = categories[i].t;
            PriorityQueue<Long> heap = new PriorityQueue<>(Comparator.reverseOrder());
            heap.add(categories[i].t);
            while (true) {
                while (j < n && categories[j].a == cur) {
                    psum += categories[j].t;
                    heap.add(categories[j++].t);
                }
                //System.out.println(String.format("%d %d %d %d", i, j, psum,heap.size()));
                if (heap.size() == 1) {
                    break;
                }
                psum -= heap.poll();
                cost += psum;
                ++cur;
            }
            i = j;
        }
        out.println(cost);
    }

    class Category {
        long a;
        long t;

        public Category(long a, long t) {
            this.a = a;
            this.t = t;
        }

        public long getA() {
            return a;
        }

        public void setA(long a) {
            this.a = a;
        }

        public long getT() {
            return t;
        }

        public void setT(long t) {
            this.t = t;
        }
    }
}
