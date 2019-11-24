package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.next().toCharArray();
        n = s.length;
        if (s.length == 1) {
            out.println(":(");
            return;
        }
        if (s[0] == ')' || s[n - 1] == '(' || n % 2 == 1) {
            out.println(":(");
            return;
        }
        s[0] = '(';
        s[n - 1] = ')';

        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> left = new LinkedList<>();
        int q = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (s[i] == '(') {
                left.addLast(i);
            } else if (s[i] == ')') {
                if (left.isEmpty()) {
                    if (stack.isEmpty()) {
                        out.println(":(");
                        return;
                    } else {
                        s[stack.getLast()] = '(';
                        stack.removeLast();
                    }
                } else {
                    left.removeLast();
//                    if (stack.isEmpty()) {
//                        left.removeLast();
//                    } else {
//                        if (stack.getLast() > left.getLast()) {
//                            s[stack.getLast()] = '(';
//                            stack.removeLast();
//                        } else {
//                            left.removeLast();
//                        }
//                    }
                }
            } else {
                if (left.isEmpty()) {
                    left.addLast(i);
                    s[i] = '(';
                } else {
                    stack.addLast(i);
                }
            }
        }
//        out.println(s);
        if (left.isEmpty() && stack.isEmpty()) {
            out.println(s);
        } else {
            while (!stack.isEmpty()) {
                if (!left.isEmpty()) {
                    int id = stack.getFirst();
                    stack.removeFirst();
                    int left_id = left.getFirst();
                    left.removeFirst();
                    if (left_id < id) {
                        s[id] = ')';
                    } else {
                        out.println(":(");
                        return;
                    }
                } else {
                    break;
                }
            }
            if (!left.isEmpty()) {
                out.println(":(");
                return;
            }
//            out.println(stack.size());
            if (stack.size() % 2 == 1) {
                out.println(":(");
                return;
            }
            while (!stack.isEmpty()) {
                int id = stack.getFirst();
//                out.println(id);
                stack.removeFirst();
                s[id] = '(';
                id = stack.getFirst();
                stack.removeFirst();
                s[id] = ')';
            }
            out.println(s);
        }
    }
}
