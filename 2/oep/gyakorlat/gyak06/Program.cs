using Gyak6.Horgaszverseny;

namespace Gyak6;

internal class Program
{
    static void Main()
    {
        
        // // így
        // int a = 0;
        // foreach (Horgasz h in ReadFile("Horgaszverseny/input.txt"))
        // {
        //     Console.WriteLine(h);
        // }
        //
        // // vagy így
        // using IEnumerator<Horgasz> enumerator = ReadFile("Horgaszverseny/input.txt").GetEnumerator();
        //
        // while (enumerator.MoveNext())
        // {
        //     Console.WriteLine(enumerator.Current.Nev);
        // }

        foreach (Horgasz h in ReadFile("Horgaszverseny/input.txt"))
        {
            if (Összsúly(h) >= 10)
            {
                Console.WriteLine(h.Nev);
            }
        }
    }

    static double Összsúly(Horgasz h)
    {
        double sum = 0;

        foreach (Fogas f in h.zsákmány)
        {
            if (f.Hossz > 0.5 && f.Fajta == "ponty")
            {
                sum += f.Súly;
            }
        }
        
        return sum;
    }

    static IEnumerable<Horgasz> ReadFile(string path)
    {
        using StreamReader sr = new StreamReader(path);

        while (!sr.EndOfStream)
        {
            string line = sr.ReadLine()!;
            string name = line.Split(";")[0];
            int fogasSb = int.Parse(line.Split(";")[1]);

            List<Fogas> fogasok = [];

            for (int i = 0; i < fogasSb; i++)
            {
                line = sr.ReadLine()!;

                Fogas fogas;
                
                string[] split = line.Split(";");
                
                fogas.Idő = split[0];
                fogas.Fajta = split[1];
                fogas.Hossz = double.Parse(split[2]);
                fogas.Súly = double.Parse(split[3]);
                
                fogasok.Add(fogas);
            }

            Horgasz h = new Horgasz();
            
            h.zsákmány = fogasok;
            h.Nev = name;
            
            yield return h;
        }
    }
}
