public class Complex {
    private double re, im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // public void add(double re, double im) {
    //     this.re += re;
    //     this.im += re;
    // }

    // public static Complex add(Complex fst, Complex snd) {
    //     return fst.add(snd);
    // }

    public double abs() {
        return Math.sqrt(re * re + im + im);
    }

    public Complex add(Complex that) {
        return new Complex(this.re + that.re, this.im + that.im);
    }

    public Complex sub(Complex that) {
        return new Complex(this.re - that.re, this.im - that.im);
    }

    public Complex mul(Complex that) {
        return new Complex(this.re * that.re - this.im * that.im);
    }

    public String toString() {
        return re + " + " + im;
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(1., 0.);
        Complex c2 = new Complex(0., 1.);

        System.out.println(c1.abs());

        //c2.add(c1);
        //Complex sum = Complex.add(c1, c2);
        Complex sum = c1.add(c2);
    }
}