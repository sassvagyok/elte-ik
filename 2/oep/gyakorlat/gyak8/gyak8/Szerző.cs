namespace gyak8;

internal class Szerző : Foglalkozás //leszármazik
{
    private List<Könyv> Művek;

    public Szerző()
    {
        Művek = [];
    }
    
    public int DarabKönyv() => Művek.Count;

    public int DarabKönyv(Kiadó kiad)
    {
        int sum = 0;

        for (int i = 0; i < Művek.Count; i++)
        {
            if (Művek[i].Kiadó == kiad)
            {
                sum++;
            }
        }
        
        return sum;
    }
}