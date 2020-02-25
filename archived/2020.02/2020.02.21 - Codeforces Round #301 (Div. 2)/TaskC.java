package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;


public class TaskC {

    char[][] map = new char[505][];
    int n, m;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};
    boolean OK = false;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        for (int i = 0; i < n; ++i) {
            map[i] = in.nextLine().toCharArray();
        }
        int r1 = in.nextInt() - 1;
        int c1 = in.nextInt() - 1;
        int r2 = in.nextInt() - 1;
        int c2 = in.nextInt() - 1;
        bfs(r1, c1, r2, c2);
        out.println(OK ? "YES" : "NO");
    }

    void bfs(int x, int y, int X, int Y) {
        LinkedList<Integer> q = new LinkedList<>();
        q.push(x);
        q.push(y);
        while (!q.isEmpty()) {
            int cx = q.pollLast();
            int cy = q.pollLast();
            for (int k = 0; k < 4; ++k) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (nx == X && ny == Y) {
                        if (map[nx][ny] == 'X') {
                            OK = true;
                            break;
                        } else {
                            int cnt = 0;
                            int cnt2 = 0;
                            for (int i = 0; i < 4; ++i) {
                                int nnx = nx + dx[i];
                                int nny = ny + dy[i];
                                if (nnx >= 0 && nnx < n && nny >= 0 && nny < m) {
                                    if (map[nnx][nny] == '.') {
                                        ++cnt;
                                    } else if (map[nnx][nny] == 'S') {
                                        cnt2++;
                                    }
                                }
                            }
                            if (cnt >= 1 || cnt2 >= 2) {
                                OK = true;
                                //System.out.println(cnt+" "+cnt2);
                                //System.out.flush();
                                break;
                            }

                        }
                    } else if (map[nx][ny] == '.') {
                        q.push(nx);
                        q.push(ny);
                        map[nx][ny] = 'S';
                    }
                }
            }
        }
    }
}
