namespace Szervizeles;

public class Munkalap
{
    private Jármű _jármű;
    private DateTime kezdet;
    private int _költség;
    private DateTime? _vég;
    private bool _javítás;
    private Szerviz _szerviz;
    private List<Munkafolyamat> munkák;
    
    public Jármű jármű
    {
        get => _jármű;
    }
    
    public Szerviz szerviz
    {
        get => _szerviz;
    }
    
    public bool javítás
    {
        get => _javítás;
    }
    
    public int költség
    {
        get => _költség;
    }
    
    public DateTime? vég
    {
        get => _vég;
    }

    public Munkalap(Szerviz sz, Jármű j, bool jav)
    {
        _jármű = j;
        kezdet = DateTime.Now;
        _költség = 0;
        _vég = null;
        _javítás = jav;
        _szerviz = sz;
        munkák = new List<Munkafolyamat>();
    }

    public void Felvesz(string m, int k)
    {
        munkák.Add(new Munkafolyamat(jármű, m, k));
    }

    public void Lezár()
    {
        _vég = DateTime.Now;
        _költség = KöltségSzámolás();
    }

    public int KöltségSzámolás()
    {
        int költségek = 0;

        foreach (Munkafolyamat m in munkák)
        {
            költségek += m.költség;
        }

        return költségek;
    }
}