namespace Szervizeles;

public class Szerviz
{
    private string _szerviz;
    private List<Munkalap> _munkalapok;
    
    public string szerviz
    {
        get => _szerviz;
    }
    
    public List<Munkalap> munkalapok
    {
        get => _munkalapok;
    }

    public Szerviz(string n)
    {
        _szerviz = n;
        _munkalapok = new List<Munkalap>();
    }

    public Munkalap Létrehoz(Jármű j, bool jav)
    {
        j.közlekedik = false;
        Munkalap m = new Munkalap(this, j, jav);
        _munkalapok.Add(m);
        return m;
    }

    public void Lezár(Munkalap m)
    {
        m.jármű.közlekedik = true;
        m.Lezár();
        
        int index = _munkalapok.IndexOf(m);
        if (index != -1)
        {
            _munkalapok[index] = m;
        }
        
        m.jármű.munkafolyamatok.Add(m);
    }
}