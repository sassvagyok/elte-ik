namespace gyak5;

internal class Program
{
    static void Main()
    {
        Numbers();
    }

    private static void CountEvens()
    {
        int dbe = 0;
        int dbu = 0;

        using StreamReader sr = new StreamReader("szamok.txt");

        if (sr.EndOfStream) return;
        
        int e = int.Parse(sr.ReadLine());

        while (!sr.EndOfStream && e > 0)
        {
            if (e % 2 == 0) dbe++;
        }

        while (!sr.EndOfStream)
        {
            if (e % 2 == 0) dbu++;
        }
        
        Console.WriteLine($"Első negaatív előtt: {dbe}");
        Console.WriteLine($"Első negatív után: {dbu}");
    }

    private static void Numbers()
    {
        using StreamReader sr = new StreamReader("pszamok1.txt");

        if (sr.EndOfStream)
        {
            return;
        }
        
        string line = sr.ReadLine();
        
        int e = int.Parse(line);

        int max = e;
        bool vanPáros = e % 2 == 0;

        while (!sr.EndOfStream)
        {
            line = sr.ReadLine();
            e = int.Parse(line);

            if (e > max)
            {
                max = e;
            }
            
            vanPáros |= e % 2 == 0;
        }
        
        Console.WriteLine($"A maximum: {max}");
        Console.WriteLine(vanPáros ? "Van pozitív" : "Nincs pozitív");
    }

    private static void Kaktuszok()
    {
        StreamReader sr = new StreamReader("kaktusz1.txt");

        List<string> y = [];
        List<string> z = [];

        while (!sr.EndOfStream)
        {
            string line = sr.ReadLine();

            Kaktusz k = Kaktusz.Parse(line);

            if (k.Szin == "piros")
            {
                y.Add(k.Nev);
            }

            if (k.Os == "Mexikó")
            {
                z.Add(k.Nev);
            }
        }
        
        sr.Close();
        
        Console.WriteLine(string.Join(",", z));
        Console.WriteLine(string.Join(",", y));
    }
}