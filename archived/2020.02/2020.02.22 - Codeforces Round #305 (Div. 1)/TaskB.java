package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Stack;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] l = new int[n];
        int[] r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.empty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            if (stack.empty()) {
                l[i] = -1;
            } else {
                l[i] = stack.peek();
            }
            stack.push(i);
        }
        stack = new Stack<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.empty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            if (stack.empty()) {
                r[i] = n;
            } else {
                r[i] = stack.peek();
            }
            stack.push(i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            //out.println(l[i] + " " + r[i]);
            int sizeOfGroup = r[i] - l[i] - 2;
            ans[sizeOfGroup] = Math.max(ans[sizeOfGroup], a[i]);
        }
        for (int i = n - 1; i > 0; --i) {
            ans[i - 1] = Math.max(ans[i - 1], ans[i]);
        }
        for (int i = 0; i < n; ++i) {
            out.print(ans[i] + " ");
        }
    }
}
