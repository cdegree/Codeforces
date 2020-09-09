package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int x = in.nextInt();
            char[] w = new char[s.length];
            int n = s.length;
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                if (i - x < 0) {
                    if (i + x < n) {
                        w[i + x] = s[i];
                    } else if (s[i] == '1') {
                        OK = false;
                    }
                } else if (i + x >= n) {
                    if (i - x >= 0 && w[i - x] == s[i]) {
                        continue;
                    } else if (i - x >= 0 && w[i - x] == 0) {
                        w[i - x] = s[i];
                    } else if (i - x >= 0) {
                        OK = false;
                    } else if (i - x < 0 && s[i] == '1') {
                        OK = false;
                    }
                }
            }
            if (OK) {
                for (int i = 0; i < n; ++i) {
                    if (0 <= i - x && i + x < n) {
                        if (s[i] == '1') {
                            if (w[i - x] == '1' || w[i + x] == '1') {

                            } else if (w[i - x] == 0) {
                                w[i - x] = '1';
                            } else if (w[i + x] == 0) {
                                w[i + x] = '1';
                            } else {
                                OK = false;
                            }
                        } else {
                            if (w[i - x] == '1') {
                                OK = false;
                            } else {
                                w[i - x] = '0';
                            }
                            if (w[i + x] == '1') {
                                OK = false;
                            } else {
                                w[i + x] = '0';
                            }
                        }
                    }
                }
                for (int i = 0; i < n; ++i) {
                    if (w[i] == 0) {
                        w[i] = '1';
                    }
                }
            }
            if (OK) {
                out.println(w);
            } else {
                out.println(-1);
            }
        }
    }
}
