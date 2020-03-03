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
        char[] s = in.nextLine().toCharArray();
        Vector<Character> list = new Vector<>();
        for (char ch : s) {
            list.add(ch);
        }
        int res = 0;
        while (true) {
            boolean remove = false;
            int id = -1;
            char mx = 'a';
            for (int i = 0; i < list.size(); ++i) {
                char ch = list.get(i);
                char pre = (char) (ch - 1);
                if ((i - 1 >= 0 && list.get(i - 1) == pre) || (i + 1 < list.size() && list.get(i + 1) == pre)) {
                    if (id == -1 || mx < ch) {
                        id = i;
                        mx = ch;
                    }
                }
            }
            if (id != -1) {
                list.remove(id);
                res++;
                remove = true;
                //System.out.println("remove " + id);
            }
            if (!remove) {
                break;
            }
        }
        out.println(res);

    }
}
