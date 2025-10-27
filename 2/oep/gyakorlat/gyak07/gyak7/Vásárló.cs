namespace gyak7;

internal class Vásárló
{
    private List<string> bevásárló_lista = [];
    public List<Termék> kosár;

    public Vásárló()
    {
        bevásárló_lista = [];
        kosár = [];
    }

    public Vásárló(List<string> lista)
    {
        bevásárló_lista = [..lista];
        kosár = [];
    }

    public void Vásárol(Üzlet s)
    {
        foreach (string név in bevásárló_lista)
        {
            (bool l, Termék termék) = Keres(név, s.Élelmiszer);
            if (l) Vesz(termék, s.Élelmiszer);
        }

        foreach (string név in bevásárló_lista)
        {
            (bool l, Termék termék) = OlcsótKeres(név, s.Műszaki);
            // Termék? t = s.Műszaki.Készlet.Where(x => x.Név == név).MinBy(x => x.Ár);
            if (l) Vesz(termék, s.Műszaki);
        }
    }

    private static (bool, Termék) Keres(string nev, Részleg r)
    {
        bool l = false;
        Termék termék = null;
        int ind = 0;
        
        while (!l && ind < r.Készlet.Count)
        {
            if (r.Készlet[ind].Név == nev)
            {
                l = true;
                termék = r.Készlet[ind];
            }
            ind++;
        }

        return (l, termék);
    }
    
    private static (bool, Termék) OlcsótKeres(string nev, Részleg r)
    {
        bool l = false;
        Termék termék = null;
        int ind = 0;
        
        while (ind < r.Készlet.Count)
        {
            if (r.Készlet[ind].Név == nev)
            {
                l = true;
                termék = r.Készlet[ind];
            } else if (l && r.Készlet[ind].Név == nev && termék!.Ár > r.Készlet[ind].Ár)
            {
                termék = r.Készlet[ind];
            }
            ind++;
        }

        return (l, termék);
    }

    private void Vesz(Termék term, Részleg r)
    {
        r.Készlet.Remove(term);
        kosár.Add(term);
        // bevásárló_lista.Remove(term.Név);
    }
}