namespace Szervizeles;

using System.Linq;

public class Önkormányzat
{
    private List<Jármű> _járművek;
    private List<Szerviz> _szervizek;
    
    public List<Jármű> jarművek
    {
        get => _járművek;
    }
    
    public List<Szerviz> szervizek
    {
        get => _szervizek;
    }

    public Önkormányzat(List<Jármű> j, List<Szerviz> s)
    {
        _járművek = j;
        _szervizek = s;
    }

    public double ElöregedettArány()
    {
        List<Jármű> elöregedett = new List<Jármű>();
        int currentYear = DateTime.Now.Year;

        foreach (Jármű j in _járművek)
        {
            if (currentYear - j.gyév >= 15)
            {
                elöregedett.Add(j);
            }
        }
        return (double)elöregedett.Count / _járművek.Count;
    }

    public double JavításArány()
    {
        return (double)_járművek.Count(x => !x.közlekedik) / _járművek.Count;
    }

    public Jármű Legdrágább()
    {
        List<(int összÁr, Jármű j)> össz = new List<(int összÁr, Jármű j)>();

        foreach (Jármű j in _járművek)
        {
            int sum = j.munkafolyamatok.Sum(x => x.költség);
            össz.Add((j.újár - j.AkutálisÉrték() + sum, j));
        }
        
        return össz.MaxBy(x => x.összÁr).j;
    }

    public void SzervizelésIndítása(Jármű j, Szerviz sz, bool jav)
    {
        sz.Létrehoz(j, jav);
    }
}