namespace Szervizeles;

public class Munkafolyamat
{
    private string _munka;
    private int _költség;
    private Jármű _jármű;

    public string munka
    {
        get => _munka;
    }
    
    public Jármű jármű
    {
        get => _jármű;
    }
    
    public int költség
    {
        get => _költség;
    }

    public Munkafolyamat(Jármű j, string m, int k)
    {
        _munka = m;
        _költség = k;
        _jármű = j;
    }
}