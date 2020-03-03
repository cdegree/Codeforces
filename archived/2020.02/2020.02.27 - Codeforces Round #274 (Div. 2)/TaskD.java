package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeSet;


public class TaskD {
    TreeSet<Integer> marks = new TreeSet<>();

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int[] a = in.nextIntArray(n);
        marks.add(0);
        int sum = x + y;
        int diff = y - x;
        boolean xOK = false;
        boolean yOK = false;
        int sumPos = -1;
        int diffPos = -1;

        for (int i = 1; i < n; ++i) {
            int cur = a[i];
            int xWant = cur - x;
            xOK = xOK|getWant(xWant);
            int yWant = cur - y;
            yOK = yOK|getWant(yWant);
            int sumWant = cur - sum;
            if (getWant(sumWant)) {
                sumPos = sumWant + x;
            }
            int diffWant = cur - diff;
            if (getWant(diffWant)) {
                if (diffWant - x >= 0) {
                    diffPos = diffWant - x;
                } else if (cur + x <= l) {
                    diffPos = cur + x;
                }
            }
            marks.add(a[i]);
        }
        if (xOK && yOK) {
            out.println(0);
        } else if (xOK) {
            out.println(1);
            out.println(y);
        } else if (yOK) {
            out.println(1);
            out.println(x);
        } else if (sumPos != -1) {
            out.println(1);
            out.println(sumPos);
        } else if (diffPos != -1) {
            out.println(1);
            out.println(diffPos);
        } else {
            out.println(2);
            out.println(x + " " + y);
        }
    }

    boolean getWant(int x) {
        if (x >= 0) {
            Integer rx = marks.floor(x);
            //System.out.println(x+" -> "+rx);
            return rx.equals(x);
        } else {
            return false;
        }
    }
}
