package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s = in.next().toCharArray();

        int n = in.nextInt();
        int q = 0;
        int p = 0;
        int mn = s.length;
        int mx = s.length;
        for (int i = 0; i < s.length; ++i) {
            if (s[i] == '?') {
                q++;
            }
            if (s[i] == '*') {
                p++;
            }
        }
        int m = s.length - p - q;
        mn = m - p - q;
        //out.println("mn = "+mn);
        if (p == 0 && m < n) {
            out.println("Impossible");
        } else if (mn > n) {
            out.println("Impossible");
        } else {
            char[] r = new char[n];
            char pre = '*';
            int j = 0;
            int c = n - mn;
            for (int i = 0; i < s.length; ++i) {
                if (s[i] == '?') {
                    if (c > 0) {
                        r[j++] = pre;
                        c--;
                    }
                } else if (s[i] == '*') {
                    while (c > 0 && j < n) {
                        r[j++] = pre;
                        --c;
                    }
                } else if(pre!='*'&&pre!='?'){
                    r[j++] = pre;
                }
                pre = s[i];
            }
            if(j<n&&pre!='*'&&pre!='?'){
                r[j++] = pre;
            }
            out.println(r);
        }
    }
}
