package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.TreeSet;
import java.util.Vector;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] A = in.nextLine().toCharArray();
            char[] B = in.nextLine().toCharArray();
            TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.charAt(1) != o2.charAt(1)) return o1.charAt(1) - o2.charAt(1);
                    else {
                        return o1.charAt(0) - o2.charAt(0);
                    }
                }
            });
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                if (A[i] > B[i]) {
                    OK = false;
                } else if (A[i] < B[i]) {
                    set.add(String.valueOf(A[i]) + String.valueOf(B[i]));
                }
            }
            Vector<char[]> pairs = new Vector<>();
            for (String s : set) {
                pairs.add(new char[]{s.charAt(0), s.charAt(1)});
            }
            boolean[] reachable = new boolean[22];
            int cnt = 0;
            for (int i = 0; i < pairs.size(); ++i) {
                char[] s = pairs.get(i);
                if (s[0] < s[1]) {
                    for (int j = i + 1; j < pairs.size(); ++j) {
                        char[] t = pairs.get(j);
                        if (t[0] == s[0] && s[1] <= t[1]) {
                            t[0] = s[1];
                        }
                    }
                    s[0] = s[1];
                    ++cnt;
                }
//                for (int k = 0; k < pairs.size(); ++k) {
//                    System.out.println(String.format("%c %c", pairs.get(k)[0], pairs.get(k)[1]));
//                }
//                System.out.println();
            }
            for (int i = 0; i < 22; ++i) {
                if (reachable[i]) {
                    ++cnt;
                }
            }
            if (!OK) {
                out.println(-1);
            } else {
                out.println(cnt );
            }
        }
    }
}
