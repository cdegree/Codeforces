package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

    }

    int pow(int x, int n) {
        if (n == 1) {
            return x;
        } else if (n % 2 == 0) {
            int ret = pow(x, n / 2);
            return ret * ret;
        } else {
            return x * pow(x, n - 1);
        }
    }

    int ask(char ch, int x, InputReader in, PrintWriter out) {
        out.println(ch + " " + x);
        out.flush();
        int ret = in.nextInt();
        return ret;
    }
}
