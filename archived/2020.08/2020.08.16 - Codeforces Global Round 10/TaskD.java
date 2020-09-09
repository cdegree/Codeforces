package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] c = in.nextLine().toCharArray();
            char[] s = new char[n + n];
            for (int i = 0; i < n; ++i) {
                s[i] = s[i + n] = c[i];
            }
            boolean[] stable = new boolean[n + n];
            for (int i = 0; i < n; ++i) {
                if (s[i] == 'R' && s[(i + 1) % n] == 'L') {
                    stable[i] = stable[(i + 1) % n] = true;
                }
            }
            int res = 0;
            for (int i = 0; i < n; ++i) {
                if (!stable[i]) {
                    stable[i] = true;
                    int st = i;
                    int ed = i;
                    int left = 0;
                    int right = 0;
                    if (s[i] == 'L') {
                        left++;
                    } else {
                        right++;
                    }
                    while (!stable[getLeft(st, n)]) {
                        stable[getLeft(st, n)] = true;
                        st = getLeft(st, n);
                        if (s[st] == 'L') {
                            left++;
                        } else {
                            right++;
                        }
                    }
                    while (!stable[getRight(ed, n)]) {
                        stable[getRight(ed, n)] = true;
                        ed = getRight(ed, n);
                        if (s[ed] == 'L') {
                            left++;
                        } else {
                            right++;
                        }
                    }
                    if(left+right==n){
                        res += getFullReverse(left);
                        res += getFullReverse(right);
                    }
                    else {
                        //System.out.println(left + " " + right);
                        res += getNeedReverse(left);
                        res += getNeedReverse(right);
                    }
                }
            }
            out.println(res);
        }
    }
    int getFullReverse(int x){
        if (x == 1) {
            return 0;
        } else {
            return (x + 2) / 3;
        }
    }

    int getNeedReverse(int x) {
        if (x == 1) {
            return 0;
        } else {
            return (x + 1) / 3;
        }
    }

    int getLeft(int i, int n) {
        return (i - 1 + n) % n;
    }

    int getRight(int i, int n) {
        return (i + 1) % n;
    }
}
