namespace gyak7;

internal class Termék
{
    public string Név { get; private set; }
    public int Ár { get; private set; }

    public Termék(string nev, int ar)
    {
        Név = nev;
        Ár = ar;
    }
}