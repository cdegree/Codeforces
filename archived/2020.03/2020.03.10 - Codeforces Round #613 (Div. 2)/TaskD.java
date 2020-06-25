package current;

import algorithms.Trie;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Trie trie = new Trie();
        for(int x:a){
            trie.add(x);
        }
        out.println(trie.getMin());
    }
}
