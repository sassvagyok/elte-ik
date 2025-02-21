namespace gyak2;

internal class Labirintus
{
    private int n;
    private int m;

    private Dictionary<Pont, Tartalom> térkép;

    public Labirintus(int n, int m)
    {
        térkép = new Dictionary<Pont, Tartalom>();
        // térkép = [];
        this.n = n;
        this.m = m;
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                térkép[new Pont(i, j)] = Tartalom.Üres;
            }
        }
    }

    public void Elehelyez(Pont poz, Tartalom t)
    {
        térkép[poz] = t;
    }

    public Tartalom Kémlel(Pont poz, Pont ir)
    {
        if (!(poz.Belül(1, n, 1, m) && ir.Irány() && (poz + ir).Belül(1, n, 1, m)))
        {
            throw new Exception();
        }

        return térkép[poz + ir];
    }

    public void Felszed(Pont poz)
    {
        if (!poz.Belül(1, n, 1, m))
        {
            throw new Exception();
        }

        if (térkép[poz] == Tartalom.Kincs)
        {
            térkép[poz] = Tartalom.Üres;
        }
    }
}

enum Tartalom
{
    Üres,
    Fal,
    Kincs,
    Szellem
}