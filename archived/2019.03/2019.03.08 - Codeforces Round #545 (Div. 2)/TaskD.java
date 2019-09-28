package current;

import algorithms.KMP;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int n = s.length;
        int m = t.length;
        int[] p = KMP.getP(t);
        int[] c = new int[2];
        for (int i = 0; i < n; ++i) {
            c[s[i] - '0']++;
        }
        int st = p[m-1];
        int j = 0;
        for(int i=0;i<n;++i){
            if( c[t[j]-'0'] >0){
                out.print(t[j]);
                c[t[j] - '0']--;
                ++j;
                if (j == t.length) {
                    j = st;
                }
            }
            else {
                if (c[0] > 0) {
                    c[0]--;
                    out.print(0);
                } else {
                    c[1]--;
                    out.print(1);
                }
            }
        }
    }
}
