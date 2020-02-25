package current;

import algorithms.Trie;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] ch = in.next().toCharArray();
        char[] good = in.next().toCharArray();
        boolean[] isGood = new boolean[26];
        for (int i = 0; i < good.length; ++i) {
            isGood[i] = good[i] == '1';
        }
        int k = in.nextInt();
        Trie trie = new Trie();
        trie.isGood = isGood;
        trie.k = k;
        for (int i = 0; i < ch.length; ++i) {
            trie.add(ch, i);
        }
        out.println(trie.result);
    }
}
