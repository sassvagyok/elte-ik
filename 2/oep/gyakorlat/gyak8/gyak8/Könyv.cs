namespace gyak8;

internal class Könyv
{
    public Kiadó Kiadó;
    public List<Szerző> szerzők;

    public Könyv(Kiadó kiadó)
    {
        Kiadó = kiadó;
        szerzők = [];
    }
}