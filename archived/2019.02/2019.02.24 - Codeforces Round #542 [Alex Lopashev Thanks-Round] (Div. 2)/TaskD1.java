package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.TreeMap;


public class TaskD1 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[m];
        int[] b = new int[m];
        int[] count = new int[n + 1];
        int[] dis = new int[n + 1];
        int mxCount = 0;
        for (int i = 0; i < m; ++i) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            int st = a[i];
            int ed = b[i];
            ++count[st];
            int len = ed - st;
            if (len < 0) {
                len += n;
            }
            if (count[st] == 1) {
                dis[st] = len;
            } else {
                dis[st] = Math.min(dis[st], len);
            }
            mxCount = Math.max(mxCount, count[st]);
        }
        int base = (mxCount - 1) * n;
//        out.println("base = " + base);


        int[] res = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            res[i] = base;
            int mx = 0;
            //out.println(String.format("count[%d] = %d",i,count[i]));
            for (int j = 1; j <= n; ++j) {
                if (mxCount == count[j]) {
                    int len = j - i;
                    if (len < 0) {
                        len += n;
                    }
                    len += dis[j];
                    if (mx < len) {
                        mx = len;
                    }
                }
                else if (mxCount == count[j] + 1) {
                    int len = j - i;
                    if (len < 0) {
                        len += n;
                    }
                    len += dis[j];
                    //out.println("ddd"+len);
                    if (len > n) {
                        len -= n;
                        if (mx < len) {
                            mx = len;
                        }
                    }
                }
            }
            res[i] += mx;
        }
        for (int i = 1; i <= n; ++i) {
            out.print(res[i] + " ");
        }
    }
}
