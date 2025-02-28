namespace gyak1;

internal class Vector
{
    double x; // ha nincs semmi elé irva, akkor private
    double y;

    public Vector(double a, double b)
    {
        x = a;
        y = b;
    }

    public static Vector Add(Vector a, Vector b)
    {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    public static double Scalar(Vector a, Vector b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static Vector operator +(Vector a, Vector b)
    {
        return Add(a, b);
    }
    
    public static double operator *(Vector a, Vector b)
    {
        return Scalar(a, b);
    }
}