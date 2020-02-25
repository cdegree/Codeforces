package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int mnCost = 100000000;
            int ansA = 1, ansB = 1, ansC = 1;
            for (int A = 1; A <= 30000; ++A) {
                for (int B = A; B <= 30000; B += A) {
                    for (int C = B; C <= 30000; C += B) {
                        int cost = Math.abs(a - A);
                        cost += Math.abs(b - B);
                        cost += Math.abs(c - C);
                        if (cost < mnCost) {
                            mnCost = cost;
                            ansA = A;
                            ansB = B;
                            ansC = C;
                        }
                    }
                }
            }
            out.println(mnCost);
            out.println(ansA + " " + ansB + " " + ansC);
        }
    }
}
