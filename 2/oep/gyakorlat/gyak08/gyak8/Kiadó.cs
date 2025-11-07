namespace gyak8;

internal class Kiadó
{
    private List<Könyv> Kiadványok;

    public Kiadó()
    {
        Kiadványok= [];
    }

    public int DarabKönyv(Szerző szer)
    {
        int sum = 0;
        
        for (int i = 0; i < Kiadványok.Count; i++)
        {
            for (int j = 0; j < Kiadványok[i].szerzők.Count; j++)
            {
                if (Kiadványok[i].szerzők[j] == szer)
                {
                    sum++;
                }
            }
        }
        
        return sum;
    }

    public string Sztár()
    {
        int max = -1;
        Szerző szerző = null;
        
        for (int i = 0; i < Kiadványok.Count; i++)
        {
            for (int j = 0; j < Kiadványok[i].szerzők.Count; j++)
            {
                if (max > Kiadványok[i].szerzők[j].DarabKönyv(this))
                {
                    szerző = Kiadványok[i].szerzők[j];
                    max = szerző.DarabKönyv(this);
                }
            }
        }

        return szerző.ToString();
    }
}