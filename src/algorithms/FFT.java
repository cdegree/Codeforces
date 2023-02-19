package algorithms;

public class FFT {

    public static void main(String[] args) {
        int[] a = {0, 1, 1, 1, 1};
        int[] b = {0, 1, 1, 1, 1};
        int pLen = a.length + b.length;
        int len = 1;
        while (len < pLen) {
            len <<= 1;
        }
        ;
        System.out.println(pLen);

        Complex[] f1 = buildComplex(a, len);
        Complex[] f2 = buildComplex(b, len);

        new FFT().fft(f1, len, 1);
        new FFT().fft(f2, len, 1);
        Complex[] f3 = new Complex[len];
        for (int i = 0; i < len; i++) {
            f3[i] = f1[i].multiply(f2[i]);
        }
        new FFT().fft(f3, len, -1);

        for (int i = 0; i < len; i++) {
            System.out.println(f3[i]);
        }
    }

    private static Complex[] buildComplex(int[] x, int len) {
        Complex[] y = new Complex[len];
        for (int i = 0; i < x.length; i++) {
            y[i] = new Complex(x[i], 0);
        }
        for (int i = x.length; i < len; i++) {
            y[i] = new Complex(0, 0);
        }
        return y;
    }

    void fft(Complex[] y, int n, int on) {
        change(y, n);
        for (int h = 2; h <= n; h <<= 1) {
            Complex wn = new Complex(Math.cos(2 * Math.PI / h), Math.sin(2 * on * Math.PI / h));
            for (int j = 0; j < n; j += h) {
                Complex w = new Complex(1, 0);
                // u = y(k), t = w*y(k+h/2)
                // u + t
                // u - t
                for (int k = j; k < j + h / 2; k++) {
                    Complex u = y[k];
                    Complex t = w.multiply(y[k + h / 2]);
                    y[k] = u.plus(t);
                    y[k + h / 2] = u.subtract(t);
                    w = w.multiply(wn);
                }
            }
        }
        if (on == -1) {
            for (int i = 0; i < n; i++) {
                y[i] = y[i].div(n);
            }
        }
    }

    /*
    x0 x1 x2 x3 x4 x5 x6 x7
    x0 x2 x4 x6 x1 x3 x5 x7
    x0 x4 x2 x6 x1 x5 x3 x7
     */
    void change(Complex[] f, int n) {
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = rev[i >> 1] >> 1;
            if ((i & 1) == 1) {
                rev[i] |= n >> 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i < rev[i]) {
                Complex t = f[i];
                f[i] = f[rev[i]];
                f[rev[i]] = t;
            }
        }
    }


    int[] a;
    int[] b;

    private void polynomialPow(int n, int max) {
        a[0] = 1;
        int bit = 1;
        int len = 0;
        while (bit <= n) {
            if ((bit & n) != 0) {
                len |= bit;
                polynomialMultiply(a, b, max * len);
            }
            bit <<= 1;
            if (bit <= n) {
                polynomialMultiply(b, b, max * bit);
            }
        }
    }

    int len;

    private void polynomialMultiply(int[] a, int[] b, int maxLen) {
        len = Integer.highestOneBit(maxLen) << 1;
        double[] aReal = new double[len];
        double[] aImage = new double[len];
        double[] bReal = new double[len];
        double[] bImage = new double[len];
        for (int i = 0; i < len; i++) {
            aReal[i] = a[i] > 0 ? 1 : 0;
            aImage[i] = 0;
            bReal[i] = b[i] > 0 ? 1 : 0;
            bImage[i] = 0;
        }
//            System.out.println(len);
        fft(aReal, aImage, 1);
        fft(bReal, bImage, 1);
        for (int i = 0; i < len; i++) {
            double tReal = aReal[i] * bReal[i] - aImage[i] * bImage[i];
            double tImage = aReal[i] * bImage[i] + aImage[i] * bReal[i];
            aReal[i] = tReal;
            aImage[i] = tImage;
        }
        fft(aReal, aImage, -1);
        for (int i = 0; i < len; i++) {
//                System.out.println(f1[i]);
            a[i] = aReal[i] > 0.5 ? 1 : 0;
        }
    }

    void fft(double[] real, double[] image, int on) {
        change(real, image);
        for (int h = 2; h <= len; h <<= 1) {
            int half = h / 2;
            double wnReal = Math.cos(2 * Math.PI / h);
            double wnImage = Math.sin(2 * on * Math.PI / h);
            for (int j = 0; j < len; j += h) {
                double wReal = 1;
                double wImage = 0;
                // u = y(k), t = w*y(k+h/2)
                for (int k = j; k < j + h / 2; k++) {
                    double uReal = real[k];
                    double uImage = image[k];
                    double tReal = real[k + half] * wReal - image[k + half] * wImage;
                    double tImage = real[k + half] * wImage + image[k + half] * wReal;
                    real[k] = uReal + tReal;
                    image[k] = uImage + tImage;
                    real[k + h / 2] = uReal - tReal;
                    image[k + h / 2] = uImage - tImage;
                    double wwReal = wReal * wnReal - wImage * wnImage;
                    double wwImage = wReal * wnImage + wImage * wnReal;
                    wReal = wwReal;
                    wImage = wwImage;
                }
            }
        }
        if (on == -1) {
            for (int i = 0; i < len; i++) {
                real[i] = real[i] / len;
            }
        }
    }

    /*
    x0 x1 x2 x3 x4 x5 x6 x7
    x0 x2 x4 x6 x1 x3 x5 x7
    x0 x4 x2 x6 x1 x5 x3 x7
     */
    void change(double[] real, double[] image) {
        int[] rev = new int[len];
        for (int i = 0; i < len; i++) {
            rev[i] = rev[i >> 1] >> 1;
            if ((i & 1) == 1) {
                rev[i] |= len >> 1;
            }
        }
        for (int i = 0; i < len; i++) {
            if (i < rev[i]) {
                double t = real[i];
                real[i] = real[rev[i]];
                real[rev[i]] = t;
                t = image[i];
                image[i] = image[rev[i]];
                image[rev[i]] = t;
            }
        }
    }
}
