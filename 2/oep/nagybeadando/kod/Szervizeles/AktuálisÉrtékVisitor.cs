namespace Szervizeles;

public class AktuálisÉrtékVisitor : IJárműVisitor
{
    public int Visit(Busz b)
    {
        double faktor = 0.0;

        if (b.övezet == "belváros" || b.övezet == "külváros")
        {
            faktor = 2.0;
        }
        else
        {
            faktor = 2.5;
        }

        return (int)Math.Round(b.újár * (100 - (DateTime.Now.Year - b.gyév)) / (100.0 * faktor));
    }
    
    public int Visit(Troli t)
    {
        double faktor = 0.0;

        if (t.övezet == "belváros")
        {
            faktor = 3.0;
        }
        else if (t.övezet == "külváros")
        {
            faktor = 3.1;
        }
        else
        {
            faktor = 3.8;
        }

        return (int)Math.Round(t.újár * (100 - (DateTime.Now.Year - t.gyév)) / (100.0 * faktor));
    }
    
    public int Visit(Villamos v)
    {
        double faktor = 0.0;

        if (v.övezet == "belváros")
        {
            faktor = 1.0;
        }
        else if (v.övezet == "külváros")
        {
            faktor = 0.9;
        }
        else
        {
            faktor = 1.2;
        }

        return (int)Math.Round(v.újár * (100 - (DateTime.Now.Year - v.gyév)) / (100.0 * faktor));
    }
}