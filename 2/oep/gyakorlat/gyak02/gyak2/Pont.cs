namespace gyak2;

internal class Pont
{
    private int x;
    private int y;

    public Pont(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public static Pont operator +(Pont a, Pont b)
    {
        return new Pont(a.x + b.x, a.y + b.y);
    }

    public bool Belül(int l, int k, int m, int n)
    {
        return (l <= y && x <= k && m <= y && y <= n);
    }

    public bool Irány()
    {
        return Belül(-1, 1, -1, 1) && ((x == 0 && y != 0) || (x != 0 && y == 0));
    }
}