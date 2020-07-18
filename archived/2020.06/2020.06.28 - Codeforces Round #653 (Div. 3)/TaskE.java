package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        Vector<Book> books = new Vector<>();
        Vector<Book> alice = new Vector<>();
        Vector<Book> bob = new Vector<>();
        for (int i = 0; i < n; ++i) {
            int t = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == 0 && b == 1) {
                bob.add(new Book(t, a, b));
            } else if (a == 1 && b == 0) {
                alice.add(new Book(t, a, b));
            } else if (a == 1 && b == 1) {
                books.add(new Book(t, a, b));
            }
        }
        Collections.sort(alice, Comparator.comparingInt(o -> o.t));
        Collections.sort(bob, Comparator.comparingInt(o -> o.t));
        for (int i = 0; i < Math.min(alice.size(), bob.size()); ++i) {
            int t = alice.get(i).t + bob.get(i).t;
            books.add(new Book(t, 1, 1));
        }
        Collections.sort(books, Comparator.comparingInt(o -> o.t));
        if (books.size() < k) {
            out.println(-1);
        } else {
            long sum = 0;
            for (int i = 0; i < k; ++i) {
                sum += books.get(i).t;
            }
            out.println(sum);
        }
    }

    class Book {
        int t;
        int a;
        int b;

        public Book(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }
    }
}
