using System;
using System.Collections.Generic;

namespace csopzh
{
    internal class Program
    {
        // deklarálás
        
        static int n;
        static Palya[] p;
        static List<int> ujraJatszani = new List<int>();
        
        struct Palya{
            public int gyemantokSzama;
            public int idoKeret;
            public bool sikerult;
            public int osszegyujtottGyemant;
            public int teljesitesiIdo;
        }
        
        static void beolvas(){
            string bemenet;
            string[] darab;
            
            Console.WriteLine("Kérem az elemek számát:");
            bemenet = Console.ReadLine();
            
            n = int.Parse(bemenet);

            if (n < 1 || n > 30)
            {
                Console.WriteLine("1 <= n <= 30");
            }
            else
            {
                p = new Palya[n + 1];

                for (int i = 1; i <= n; i++)
                {
                    bemenet = Console.ReadLine();
                    darab = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);
                    if (int.Parse(darab[0]) < 1 || int.Parse(darab[0]) > 20)
                    {
                        Console.WriteLine("1 <= gyemantokSzama <= 20");
                    }
                    else
                    {
                        p[i].gyemantokSzama = int.Parse(darab[0]);
                    }
                    
                    if (int.Parse(darab[1]) < 20 || int.Parse(darab[1]) > 300)
                    {
                        Console.WriteLine("20 <= idoKeret <= 300");
                    }
                    else
                    {
                        p[i].idoKeret= int.Parse(darab[1]);
                    }
                    
                    p[i].sikerult = bool.Parse(darab[2]);
                    p[i].osszegyujtottGyemant= int.Parse(darab[3]);
                    p[i].idoKeret= int.Parse(darab[4]);
                }
            }
        }
        
        static void Main(string[] args)
        {
            // beolvasás
            beolvas();

            // kiválogatás tétel
            int db = 0;
            for (int i = 1; i <= n; i++)
            {
                if (!(p[i].sikerult && p[i].osszegyujtottGyemant == p[i].gyemantokSzama &&
                    p[i].teljesitesiIdo <= p[i].idoKeret))
                {
                    db += 1;
                    ujraJatszani.Add(i);
                }
            }
            
            // kiírás
            Console.WriteLine("Pályák, amiket újra akarok játszani:");
            Console.WriteLine(string.Join(" ", ujraJatszani));
        }
    }
}