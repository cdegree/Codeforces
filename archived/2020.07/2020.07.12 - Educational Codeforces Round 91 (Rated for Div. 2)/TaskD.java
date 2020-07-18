package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long x = in.nextLong();
        int k = in.nextInt();
        long y = in.nextLong();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(m);
        int preI = -1;
        int i = 0;
        int j = 0;
        boolean OK = true;
        long cost = 0;
        while (i <= n || j < m) {
            if (i < n && (j >= m || a[i] != b[j])) {
                ++i;
            } else {
                if (i >= n && j < m) {
                    OK = false;
                    break;
                }
                //System.out.println(i + " " + j + " " + cost);

                int nowI = i;
                int len = nowI - preI - 1;
                //System.out.println("len = " + len);
                if (k * y >= x) {
                    if (len >= k) {
                        cost += len / k * x + len % k * y;
                        //System.out.println(i + " " + j + " " + cost);
                    } else {
                        int mx = 0;
                        for (int p = preI + 1; p < nowI; ++p) {
                            if (mx < a[p]) {
                                mx = a[p];
                            }
                        }
                        Vector<Integer> boarder = new Vector<>();
                        if (preI > 0) {
                            boarder.add(a[preI]);
                        }
                        if (nowI < n) {
                            boarder.add(a[nowI]);
                        }
                        boarder.sort(Comparator.comparingInt(o -> o));
                        if (!boarder.isEmpty() && boarder.lastElement() < mx) {
                            OK = false;
                        } else {
                            cost += len * y;
                            //System.out.println(i + " " + j + " " + cost);
                        }
                    }
                } else {
                    int mx = 0;
                    for (int p = preI + 1; p < nowI; ++p) {
                        if (mx < a[p]) {
                            mx = a[p];
                        }
                    }
                    Vector<Integer> boarder = new Vector<>();
                    if (preI > 0) {
                        boarder.add(a[preI]);
                    }
                    if (nowI < n) {
                        boarder.add(a[nowI]);
                    }
                    boarder.sort(Comparator.comparingInt(o -> o));
                    if (!boarder.isEmpty() && boarder.lastElement() < mx) {
                        if (len >= k) {
                            cost += x + y * (len - k);
                        } else {
                            OK = false;
                        }
                    } else {
                        cost += len * y;
                    }
                }
                preI = i;
                ++i;
                ++j;
            }
        }
        if (OK) {
            out.println(cost);
        } else {
            out.println(-1);
        }
    }
}
