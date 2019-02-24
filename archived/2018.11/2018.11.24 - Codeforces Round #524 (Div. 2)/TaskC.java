package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int x3 = in.nextInt();
            int y3 = in.nextInt();
            int x4 = in.nextInt();
            int y4 = in.nextInt();
            int[] inter = getIntect(x1, y1, x2, y2, x3, y3, x4, y4);

            long[] twb = get_white_black(1, 1, n, m);

            if (inter == null) {
                long[] wb = get_white_black(x1, y1, x2, y2);
                twb[1] -= wb[1];
                twb[0] += wb[1];
                wb = get_white_black(x3, y3, x4, y4);
                twb[0] -= wb[0];
                twb[1] += wb[0];
            } else {
                long[] wb = get_white_black(x1, y1, x2, y2);
                twb[1] -= wb[1];
                twb[0] += wb[1];
                wb = get_white_black(x3, y3, x4, y4);
                twb[0] -= wb[0];
                twb[1] += wb[0];
                wb = get_white_black(inter[0], inter[1], inter[2], inter[3]);
                twb[1] += wb[1];
                twb[0] -= wb[1];
            }
            out.println(twb[0] + " " + twb[1]);
        }
    }

    int[] getIntect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] interX = getIntersact(x1, x2, x3, x4);
        int[] interY = getIntersact(y1, y2, y3, y4);
        if (interX != null && interY != null) {
            return new int[]{interX[0], interY[0], interX[1], interY[1]};
        } else {
            return null;
        }
    }

    int[] getIntersact(int x1, int x2, int x3, int x4) {
        int left = Math.max(x1, x3);
        int right = Math.min(x2, x4);
        if (left <= right) {
            return new int[]{left, right};
        } else {
            return null;
        }
    }

    long[] get_white_black(int x1, int y1, int x2, int y2) {
        long lenx = x2 - x1 + 1;
        long leny = y2 - y1 + 1;
        if (lenx % 2 == 1 && leny % 2 == 1) {
            if ((x1 + y1) % 2 == 0) {
                return new long[]{lenx * leny / 2 + 1, lenx * leny / 2};
            } else {
                return new long[]{lenx * leny / 2, lenx * leny / 2 + 1};
            }
        } else {
            return new long[]{lenx * leny / 2, lenx * leny / 2};
        }
    }
}
