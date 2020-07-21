package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            char[] t = "abacaba".toCharArray();
            int cnt = count(s, t);
            if (cnt == 1) {
                out.println("YES");
                for (int i = 0; i < n; ++i) {
                    if (s[i] == '?') {
                        s[i] = 'd';
                    }
                }
                out.println(s);
            } else if (cnt >= 2) {
                out.println("NO");
            } else {
                Vector<Vector<Pair>> cand = new Vector<>();
                boolean found = false;
                for (int st = 0; st < n; ++st) {
                    boolean same = true;
                    Vector<Pair> pairs = new Vector<>();
                    for (int i = 0; i < t.length; ++i) {
                        if (st + i >= n) {
                            same = false;
                            break;
                        } else if (s[st + i] == t[i]) {

                        } else if ((s[st + i] != t[i])) {
                            if (s[st + i] == '?') {
                                pairs.add(new Pair(st + i, t[i]));
                            } else {
                                same = false;
                                break;
                            }
                        }
                    }
                    if (same) {
                        for (Pair p : pairs) {
                            s[p.p] = p.c;
                        }
                        cnt = count(s, t);
                        if (cnt == 1) {
                            found = true;
                            break;
                        }
                        else{
                            for (Pair p : pairs) {
                                s[p.p] = '?';
                            }
                        }
                    }
                }
                if(found){
                    out.println("YES");
                    for (int i = 0; i < n; ++i) {
                        if (s[i] == '?') {
                            s[i] = 'd';
                        }
                    }
                    out.println(s);
                }
                else{
                    out.println("NO");
                }
            }
        }
    }

    int count(char[] s, char[] t) {
        int n = s.length;
        int cnt = 0;
        for (int st = 0; st < n; ++st) {
            boolean same = true;
            for (int i = 0; i < t.length; ++i) {
                if (st + i >= n || s[st + i] != t[i]) {
                    same = false;
                    break;
                }
            }
            if (same) {
                ++cnt;
            }
        }
        return cnt;
    }

    class Pair {
        int p;
        char c;

        public Pair(int p, char c) {
            this.p = p;
            this.c = c;
        }
    }
}
