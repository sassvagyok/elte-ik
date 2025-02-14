aaa
    
    
6
1 11 10 3
2 22 10 2
2 22 11 4
1 11 18 9
4 4 19 1
11 11 25 3

using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    internal class Program
    {
        static int n;
        static Adat[] bor;

        struct Adat
        {
            public int mennyiseg;
            public int ar;
        }

        static void beolvas()
        {
            string bemenet;
            string[] darabok;

            bemenet = Console.ReadLine();
            n = int.Parse(bemenet);

            bor = new Adat[n + 1];

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

                bor[i].mennyiseg = int.Parse(darabok[0]);
                bor[i].ar = int.Parse(darabok[1]);
            }
        }

        static bool nemVoltElotte(int i)
        {
            int j;
            j = 1;

            while (j <= i - 1 && bor[j].ar != bor[i].ar)
            {
                j = j + 1;
            }

            return j > i - 1;
        }

        static bool tobbMintElotte(int i)
        {
            int j;

            j = 1;

            while (j <= i - 1 - 1 && bor[j].mennyiseg < bor[i].mennyiseg)
            {
                j = j + 1;
            }
            return j > i - 1;
        }

        static void Main(string[] args)
        {
            beolvas();

            // a)
            int legkisebbTermelesuIndex;
            int minert;

            legkisebbTermelesuIndex = 1;
            minert = bor[1].mennyiseg;

            for (int i = 1; i <= n; ++i)
            {
                if (bor[i].mennyiseg < minert)
                {
                    minert = bor[i].mennyiseg;
                    legkisebbTermelesuIndex = i;
                }
            }

            Console.WriteLine(legkisebbTermelesuIndex);

            // b)
            int maxAr1000Felett = 0;
            bool van;

            van = false;

            for (int i = 1; i <= n; ++i)
            {
                if (bor[i].mennyiseg <= 1000)
                {
                    
                }
                else if (bor[i].mennyiseg > 1000 && van)
                {
                    maxAr1000Felett = bor[i].ar;
                }
                else
                {
                    maxAr1000Felett = bor[i].ar;
                    van = true;
                }
            }

            if (!van)
            {
                maxAr1000Felett = -1;
            }
            
            Console.WriteLine(maxAr1000Felett);
            
            // c)
            int borArDarab;

            borArDarab = 0;

            for (int i = 1; i <= n; ++i)
            {
                if (nemVoltElotte(i))
                {
                    borArDarab = borArDarab + 1;
                }
            }
            
            Console.WriteLine(borArDarab);
            
            // d)
            int tobbMintElotteDb = 0;
            
            List<int> tobbMintElotteIndexek = new List<int>();

            for (int i = 2; i <= n; ++i)
            {
                if (tobbMintElotte(i))
                {
                    tobbMintElotteDb = tobbMintElotteDb + 1;
                    tobbMintElotteIndexek.Add(i);
                }
            }
            
            Console.WriteLine(tobbMintElotteDb + " " + string.Join(" ", tobbMintElotteIndexek));
        }
    }
}
