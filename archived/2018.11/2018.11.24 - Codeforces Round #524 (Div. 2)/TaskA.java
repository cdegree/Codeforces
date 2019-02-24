package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] need = {2, 5, 8};
        long sum=0;
        for (int i = 0; i < need.length; ++i) {
            need[i] = need[i] * n;
            sum += (need[i]+k-1)/k;
        }
        out.println(sum);
    }
}
