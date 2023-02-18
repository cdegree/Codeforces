package algorithms;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Fraction {
    BigInteger numerator;
    BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        checkZero(this.denominator);
        reduce();
    }

    public Fraction(long numerator, long denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
        checkZero(this.denominator);
        reduce();
    }

    public Fraction(long numerator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.ONE;
    }

    public static void main(String[] args) {

        System.out.println(35 * 34 * 33 * 32 * 31 / 5 / 4 / 3 / 2 * 12 * 11 / 2);

        System.out.println(new Fraction(9, 12).power(10));

        int n = 1000000;
        long total = 21425712;
        for (int i = 1; i < n; i *= 10) {
            calculateProbability(n / i, total, i);
        }
    }

    private static void calculateProbability(long buy, long total, long n) {
        Fraction notBuy = new Fraction(total - buy, total);
        Fraction notHit = notBuy.power(n);
        Fraction hit = new Fraction(1).subtract(notHit);
        System.out.println(String.format("buy %d by %d times = %s", buy, n, hit.toApproximatelyValue(18).toPlainString()));
        System.out.flush();
    }

    private void reduce() {
        BigInteger gcd = this.denominator.gcd(this.numerator);
        this.denominator = this.denominator.divide(gcd);
        this.numerator = this.numerator.divide(gcd);
    }

    private void checkZero(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("zero");
        }
    }

    public Fraction copy() {
        return new Fraction(this.numerator, this.denominator);
    }

    public Fraction add(Fraction other) {
        BigInteger numerator = this.numerator.multiply(other.denominator).add(this.denominator.multiply(other.numerator));
        BigInteger denominator = this.denominator.multiply(other.denominator);
        return new Fraction(numerator, denominator);
    }

    public Fraction subtract(Fraction other) {
        BigInteger numerator = this.numerator.multiply(other.denominator).subtract(this.denominator.multiply(other.numerator));
        BigInteger denominator = this.denominator.multiply(other.denominator);
        return new Fraction(numerator, denominator);
    }

    public Fraction multiply(Fraction other) {
        BigInteger numerator = this.numerator.multiply(other.numerator);
        BigInteger denominator = this.denominator.multiply(other.denominator);
        return new Fraction(numerator, denominator);
    }

    public Fraction divide(Fraction other) {
        BigInteger numerator = this.numerator.multiply(other.denominator);
        BigInteger denominator = this.denominator.multiply(other.numerator);
        return new Fraction(numerator, denominator);
    }

    public Fraction power(long n) {
        Fraction unit = this.copy();
        if (n == 1) {
            return unit;
        } else if (n % 2 == 0) {
            Fraction half = power(n / 2);
            return half.multiply(half);
        } else {
            Fraction half = power(n / 2);
            return half.multiply(half).multiply(unit);
        }
    }

    public BigDecimal toApproximatelyValue(int scale) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), scale, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
