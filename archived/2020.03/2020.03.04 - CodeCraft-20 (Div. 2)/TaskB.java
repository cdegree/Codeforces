package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

//        String ss = "abcdefg";
//
//        for (int len = 1; len <= ss.toCharArray().length; ++len) {
//
//            char[] a = ss.toCharArray();
//            out.print("len = " + len + "   ");
//            for (int i = 0; i + len <= a.length; ++i) {
//                ArrayUtils.reverse(a, i, i + len);
//            }
//
//        }
//        out.flush();


        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            String s = in.nextLine();
            char[] origin = s.toCharArray();
            Vector<Mf> mfs = new Vector<>();
            for (int len = 1; len <= origin.length; ++len) {
                char[] t = new char[origin.length];
                for (int i = len - 1; i < origin.length; ++i) {
                    t[i - len + 1] = origin[i];
                }
                char[] p = new char[len - 1];
                for (int i = 0; i < len - 1; ++i) {
                    p[i] = origin[i];
                }
                if ((t.length - len) % 2 == 0) {
                    ArrayUtils.reverse(p, 0, len - 1);
                } else {
                }
                for (int i = 0; i < len - 1; ++i) {
                    t[origin.length - len + 1 + i] = p[i];
                }
                mfs.add(new Mf(t,len));
//                System.out.print(String.format("len = %d ", len));
//                for (int i = 0; i < t.length; ++i) {
//                    System.out.print(t[i]);
//                }
//                System.out.println();
            }
            Collections.sort(mfs, new Comparator<Mf>() {
                @Override
                public int compare(Mf o1, Mf o2) {
                    for(int i=0;i<o1.s.length;++i){
                        if(o1.s[i]!=o2.s[i]){
                            return o1.s[i]-o2.s[i];
                        }
                    }
                    return o1.k-o2.k;
                }
            });
            //System.out.print(String.format("len = %d ", len));
            for (int i = 0; i < mfs.get(0).s.length; ++i) {
                out.print(mfs.get(0).s[i]);
            }
            out.println();
            out.println(mfs.get(0).k);
        }
    }

    class Mf {
        char[] s;
        int k;

        public Mf(char[] s, int k) {
            this.s = s;
            this.k = k;
        }
    }
}
