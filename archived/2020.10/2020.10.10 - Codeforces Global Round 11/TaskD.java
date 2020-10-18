package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray1(n);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int cnt=0;
        while (!isSorted(a)&&++cnt<100) {
            int x = -1;
            int y = -1;
            for (int i = 1; i <= n && x == -1; ++i) {
                for (int j = i + 1; j <= n; ++j) {
                    if (a[i] == a[j] + 1) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            ArrayList<Integer> list = new ArrayList<>();
            if (x > 1) {
                list.add(x - 1);
            }
            int right = x;
            for (int i = x; i <= n; ++i) {
                if (i - x == a[i] - a[x]) {
                    right = i;
                } else {
                    break;
                }
            }
            list.add(right - x + 1);
            list.add(y - right);
            if (y < n) {
                list.add(n - y);
            }
            res.add(list);
            deckReverse(a, list);
//            System.out.println(list);
//            System.out.print("cnt = "+cnt+"  ");
//            for (int i = 1; i <= n; ++i) {
//                System.out.print(a[i] + " ");
//            }
//            System.out.println();
        }
        out.println(res.size());
        for (ArrayList<Integer> list : res) {
            out.print(list.size());
            for (int x : list) {
                out.print(" " + x);
            }
            out.println();
        }
    }

    boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            if (a[i] != i) return false;
        }
        return true;
    }

    void deckReverse(int[] a, ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> collect = new ArrayList<>();
        int j = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i < a.length; ++i) {
            temp.add(a[i]);
            if(temp.size()==list.get(j)){
                collect.add(temp);
                temp = new ArrayList<>();
                ++j;
            }
        }
        //System.out.println(collect);
        j = 1;
        for (int i = collect.size() - 1; i >= 0; --i) {
            for (int x : collect.get(i)) {
                a[j++] = x;
            }
        }
    }
}
