package algorithms;

public class KMP {
    static public int[] getP(char[] s) {
        int n = s.length;
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; ++i) {
            while (j > 0 && s[i] != s[j]) {
                j = p[j - 1];
            }
            if (s[i] == s[j]) {
                ++j;
            }
            p[i] = j;
        }
        return p;
    }

    static public void main(String[] args) {
        char[] s = "abababcdabcabcd".toCharArray();
        int[] p = getP(s);
        for (int i = 0; i < s.length; ++i) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < p.length; ++i) {
            System.out.print(p[i] + " ");
        }


    }
}
