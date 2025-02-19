namespace gyak2;

internal class Rac
{
    int d;
    int n;
    
    // Tartalom[,] ez a jo

    public Rac(int i, int j)
    {
        if (j == 0) throw new DivideByZeroException();
        
        n = i;
        d = j;
    }

    public static Rac operator +(Rac a, Rac b)
    {
        return new Rac(a.n + a.d * b.n * b.d, a.d * b.d);
    }
    
    public static Rac operator -(Rac a, Rac b)
    {
        return new Rac(a.n - a.d * b.n * b.d, a.d * b.d);
    }
    
    public static Rac operator *(Rac a, Rac b)
    {
        return new Rac(a.n * b.n, a.d * b.d);
    }
    
    public static Rac operator /(Rac a, Rac b)
    {
        if (b.n == 0)
        {
            throw new DivideByZeroException();
        }
        
        return a * new Rac(b.d, b.n);
    }

    public override string ToString()
    {
        if (n * d < 0)
        {
            return $"-{Math.Abs(n)} / {Math.Abs(d)}";
        }
        return $"{Math.Abs(n)} / {Math.Abs(d)}";
    }
}