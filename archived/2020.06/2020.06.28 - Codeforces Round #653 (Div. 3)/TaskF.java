package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Vector<Book> books = new Vector<>();
        Vector<Book> alice = new Vector<>();
        Vector<Book> bob = new Vector<>();
        Vector<Book> nolikes = new Vector<>();
        Vector<Book> rawBooks = new Vector<>();
        for (int i = 0; i < n; ++i) {
            int t = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == 0 && b == 1) {
                bob.add(new Book(t, a, b, i));
            } else if (a == 1 && b == 0) {
                alice.add(new Book(t, a, b, i));
            } else if (a == 1 && b == 1) {
                books.add(new Book(t, a, b, i));
            } else {
                nolikes.add(new Book(t, a, b, i));
            }
            rawBooks.add(new Book(t, a, b, i));
        }
        alice.sort(Comparator.comparingInt(o -> o.t));
        bob.sort(Comparator.comparingInt(o -> o.t));
        if (2 * k <= m) {
            for (int i = 0; i < Math.min(alice.size(), bob.size()); ++i) {
                int t = alice.get(i).t + bob.get(i).t;
                books.add(new Book(t, 1, 1, alice.get(i).rawIndex, bob.get(i).rawIndex));
            }
            books.sort(Comparator.comparingInt(o -> o.t));
            if (books.size() < k) {
                out.println(-1);
            } else {
                long sum = 0;
                Vector<Integer> res = new Vector<>();

                for (int i = 0; i < k; ++i) {
                    if (books.get(i).rawIndex != -1) {
                        res.add(books.get(i).rawIndex);
                        rawBooks.get(books.get(i).rawIndex).used = true;
                    } else {
                        res.add(books.get(i).rawIndexA);
                        res.add(books.get(i).rawIndexB);
                        rawBooks.get(books.get(i).rawIndexA).used = true;
                        rawBooks.get(books.get(i).rawIndexB).used = true;
                    }
                    books.get(i).used = true;
                    sum += books.get(i).t;
                }
                rawBooks.sort(Comparator.comparingInt(o -> o.t));
                int j = 0;
                while (res.size() < m) {
                    while (j < rawBooks.size() && rawBooks.get(j).used) ++j;
                    res.add(rawBooks.get(j).rawIndex);
                    sum += rawBooks.get(j).t;
                    ++j;
                }
                out.println(sum);
                for (int x : res) {
                    out.print((x + 1) + " ");
                }
                out.println();
            }
        } else {
            int sharedSize = 2 * k - m;
            int independentSize = m - sharedSize;
            int singleSize = k - sharedSize;
            books.sort(Comparator.comparingInt(o -> o.t));
            if (books.size() < sharedSize) {
                out.println(-1);
            } else {
                Vector<Integer> res = new Vector<>();
                long sum = 0;
                for (int i = 0; i < sharedSize; ++i) {
                    books.get(i).used = true;
                    rawBooks.get(books.get(i).rawIndex).used = true;
                    res.add(books.get(i).rawIndex);
                    sum += books.get(i).t;
                }
                for (int i = 0; i < Math.min(alice.size(), bob.size()); ++i) {
                    int t = alice.get(i).t + bob.get(i).t;
                    books.add(new Book(t, 1, 1, alice.get(i).rawIndex, bob.get(i).rawIndex));
                }
                if (books.size() < k) {
                    out.println(-1);
                } else {
                    books.sort(Comparator.comparingInt(o -> o.t));
                    for (int i = 0; i < books.size(); ++i) {
                        if (books.get(i).used) continue;
                        if (singleSize <= 0) break;
                        --singleSize;
                        if (books.get(i).rawIndex != -1) {
                            res.add(books.get(i).rawIndex);
                            rawBooks.get(books.get(i).rawIndex).used = true;
                        } else {
                            res.add(books.get(i).rawIndexA);
                            res.add(books.get(i).rawIndexB);
                            rawBooks.get(books.get(i).rawIndexA).used = true;
                            rawBooks.get(books.get(i).rawIndexB).used = true;
                        }
                        books.get(i).used = true;
                        sum += books.get(i).t;
                    }
                    rawBooks.sort(Comparator.comparingInt(o -> o.t));
                    int j = 0;
                    while (res.size() < m) {
                        while (j < rawBooks.size() && rawBooks.get(j).used) ++j;
                        res.add(rawBooks.get(j).rawIndex);
                        sum += rawBooks.get(j).t;
                        ++j;
                    }
                    out.println(sum);
                    for (int x : res) {
                        out.print((x + 1) + " ");
                    }
                    out.println();
                }
            }
        }
    }

    class Book {
        int t;
        int a;
        int b;
        int rawIndex = -1;
        int rawIndexA = -1;
        int rawIndexB = -1;
        boolean used = false;

        public Book(int t, int a, int b, int rawIndex) {
            this.t = t;
            this.a = a;
            this.b = b;
            this.rawIndex = rawIndex;
        }

        public Book(int t, int a, int b, int rawIndexA, int rawIndexB) {
            this.t = t;
            this.a = a;
            this.b = b;
            this.rawIndexA = rawIndexA;
            this.rawIndexB = rawIndexB;
        }
    }
}
