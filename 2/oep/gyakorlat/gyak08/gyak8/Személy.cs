namespace gyak8;

internal class Személy
{
    public string Név { get; private set; }
    public string TAJ { get; private set; }
    public string Adó { get; private set; }

    public List<Foglalkozás> állás;

    public Személy(string nev, string taj, string ado, List<Foglalkozás> állás)
    {
        Név = nev;
        TAJ = taj;
        Adó = ado;
        this.állás = new(állás);
    }
}