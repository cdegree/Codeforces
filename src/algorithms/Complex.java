package algorithms;

public class Complex {
    double real;
    double image;

    public Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    public double getReal() {
        return real;
    }

    public double getImage() {
        return image;
    }

    public Complex plus(Complex other) {
        return new Complex(real + other.real, image + other.image);
    }

    public Complex subtract(Complex other) {
        return new Complex(real - other.real, image - other.image);
    }
    public Complex div(double v) {
        return new Complex(real / v, image / v);
    }

    public Complex multiply(Complex other) {
        double nReal = real * other.real - image * other.image;
        double nImage = real * other.image + image * other.real;
        return new Complex(nReal, nImage);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", image=" + image +
                '}';
    }
}
