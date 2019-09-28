package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();

        int[] t = new int[4];
        int[] c = new int[4];
        int[] ta = new int[4];
        int[] tb = new int[4];
        int[] tp = new int[n];

        for (int i = 0; i < n; ++i) {
            if (a[i] == '0' && b[i] == '0') {
                t[0]++;
                tp[i] = 0;
            }
            if (a[i] == '1' && b[i] == '0') {
                t[1]++;
                tp[i] = 1;
            }
            if (a[i] == '1' && b[i] == '1') {
                t[2]++;
                tp[i] = 2;
            }
            if (a[i] == '0' && b[i] == '1') {
                t[3]++;
                tp[i] = 3;
            }
        }
        boolean ok = false;

        for (int B = 0; B <= t[1]; ++B) {
            for (int C = 0; C <= t[2]; ++C) {

                int Aone = B + C;
                int Bone = t[2] - C;
                int Db = Aone - Bone;
                int D = t[3] - Db;
                int A = n / 2 - B - C - D;
//                out.println(String.format("%d %d %d %d", A, B, C, D));

                if (A >= 0 && A <= t[0] && D >= 0 && D <= t[3]) {
                    ta[0] = A;
                    ta[1] = B;
                    ta[2] = C;
                    ta[3] = D;
                    ok = true;
                }
            }
        }


//        for (int i = 0; i < 4; ++i) {
//            out.println(ta[i] + "  " + tb[i]);
//        }

        if (ok) {
            for (int i = 0; i < n; ++i) {
                if (ta[tp[i]] > 0) {
                    ta[tp[i]]--;
                    out.print(i + 1 + " ");
                }
            }
            out.println();
        } else {
            out.println(-1);
        }
    }
}
