namespace gyak8_2;

internal class Parcella
{
    private int ültetésiidő;
    private Növényfajta? fajta;

    public void Ültet(Növényfajta növ)
    {
        if (fajta is null)
        {
            throw new InvalidOperationException();
        }
        
        fajta = növ;
        ültetésiidő = JelenHónap();
    }

    public bool Beérik(int hónap)
    {
        return fajta is not null && fajta is Zöldség && hónap - ültetésiidő == fajta.Érésiidő;
    }

    public void Arat()
    {
        fajta = null;
    }
    
    private int JelenHónap() => Random.Shared.Next(1, 13);
}