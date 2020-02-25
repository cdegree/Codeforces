package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[][] s = new char[n][];
        for (int i = 0; i < n; ++i) {
            s[i] = in.nextLine().toCharArray();
        }
        Vector<Integer> towards[] = new Vector[26];
        for (int i = 0; i < 26; ++i) towards[i] = new Vector<>();
        int[] ind = new int[26];
        boolean OK = true;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = -1;
                int b = -1;
                for (int k = 0; k < Math.min(s[i].length, s[j].length); ++k) {
                    if (s[i][k] != s[j][k]) {
                        a = s[i][k] - 'a';
                        b = s[j][k] - 'a';
                        break;
                    }
                }
                if (a != -1) {
                    ind[b]++;
                    towards[a].add(b);
                } else {
                    if (s[i].length > s[j].length) {
                        OK = false;
                    }
                }
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            if (ind[i] == 0) {
                queue.addLast(i);
            }
        }
        Vector<Character> result = new Vector<>();
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            result.add((char) (cur + 'a'));
            for (int j = 0; j < towards[cur].size(); ++j) {
                int v = towards[cur].get(j);
                ind[v]--;
                if (ind[v] == 0) {
                    queue.addLast(v);
                }
            }
        }
        if (result.size() == 26 && OK) {
            for (int i = 0; i < 26; ++i) out.print(result.get(i));
        } else {
            out.println("Impossible");
        }
    }
}
