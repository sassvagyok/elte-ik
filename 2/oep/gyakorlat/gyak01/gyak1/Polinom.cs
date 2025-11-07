namespace gyak1;

internal class Polinom
{
    private double a;
    private double b;
    private double c;

    public Polinom(double x, double y, double z)
    {
        a = x;
        b = y;
        c = z;
    }

    public double Value(double x) => a * Math.Pow(x, 2) + b * x + c;
}