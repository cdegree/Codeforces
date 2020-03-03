package current;

import algorithms.Trie;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Trie trie = new Trie();
        for (int i = 0; i < n; ++i) {
            char[] s = in.nextLine().toCharArray();
            trie.add(s);
        }
        for (int i = 0; i < m; ++i) {
            char[] t = in.nextLine().toCharArray();
            out.println(Utils.YESNO(trie.isExist(t)));
        }
    }
}
