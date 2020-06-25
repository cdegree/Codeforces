package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();


        while (tt-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int best = 1;
            int[] cnt = new int[26];
            for (char ch : s) {
                cnt[ch - 'a']++;
                if (cnt[ch - 'a'] > 0) {
                    //System.out.println(String.format("cnt[%c] = %d",ch ,cnt[ch - 'a']));
                }
            }

            for (int len = 1; len <= n; ++len) {
                int mul = 0;
                for (int i = 0; i < 26; ++i) {
                    mul += cnt[i] / len;
                }
                for (int j = 1; j <= mul; ++j) {
                    if (k % j == 0) {
                        best = Math.max(best, j * len);
                    }
                }
            }
            out.println(best);
        }
    }
}
