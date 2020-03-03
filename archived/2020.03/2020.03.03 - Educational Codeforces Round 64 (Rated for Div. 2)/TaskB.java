package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            String s = in.nextLine();
            Vector<Character> odd = new Vector<>();
            Vector<Character> even = new Vector<>();
            for (char ch : s.toCharArray()) {
                if (ch % 2 == 0) {
                    odd.add(ch);
                } else {
                    even.add(ch);
                }
            }
            Collections.sort(odd);
            Collections.sort(even);
            Vector<Character> r1 = new Vector<>(odd);
            r1.addAll(even);
            Vector<Character> r2 = new Vector<>(even);
            r2.addAll(odd);
            if (check(r1)) {
                pf(r1, out);
            } else if (check(r2)) {
                pf(r2, out);
            } else {
                out.println("No answer");
            }
        }
    }

    void pf(Vector<Character> r, PrintWriter out) {
        for (char ch : r) {
            out.print(ch);
        }
        out.println();
    }

    boolean check(Vector<Character> s) {
        for (int i = 1; i < s.size(); ++i) {
            if (Math.abs(s.get(i) - s.get(i - 1)) == 1) {
                return false;
            }
        }
        return true;
    }
}
