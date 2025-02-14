using System;
using System.Collections.Generic;

namespace gyak12
{
    internal class Program
    {
        static int[] sajatSzamok;
        static int szelvenyAr;
        static int[] nyeremenyek;
        static int[,] kihuzottSzamok;

        static void Beolvas()
        {
            string bemenet;
            string[] darabok;

            // Sajat szamok
            bemenet = Console.ReadLine();
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            sajatSzamok = new int[6];
            for (int i = 1; i <= 5; i++)
            {
                sajatSzamok[i] = int.Parse(darabok[i - 1]);
            }

            // Szelveny ara
            bemenet = Console.ReadLine();
            szelvenyAr = int.Parse(bemenet);

            // Nyeremenyek
            bemenet = Console.ReadLine();
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);
            nyeremenyek = new int[5];

            for (int i = 1; i <= 4; i++)
            {
                nyeremenyek[i] = int.Parse(darabok[i - 1]);
            }

            // Kihuzott szamok
            kihuzottSzamok = new int[53, 6];
            for (int i = 1; i <= 52; i++)
            {
                bemenet = Console.ReadLine();
                darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

                for (int j = 1; j <= 5; j++)
                {
                    kihuzottSzamok[i, j] = int.Parse(darabok[j - 1]);
                }
            }
        }

        static bool szerepel(int j, int i)
        {
            int k;
            k = 1;

            while (k <= 5 && sajatSzamok[j] != kihuzottSzamok[i, k])
            {
                k += 1;
            }

            return k <= 5;
        }

        static bool nyer(int i)
        {
            int db;
            db = 0;

            for (int j = 1; j <= 5; j++)
            {
                fif (szerepel(j, i))
                {
                    db += 1;
                }
            }

            return db >= 2;
        }

        static int leghosszabb(int i)
        {
            bool van;
            int j;

            j = i;
            while (j <= 52 && !nyer(j))
            {
                j += 1;
            }

            van = j <= 52;

            if(van)
            {
                return j - 1;
            }
            else
            {
                return 52;
            }
        }

        static void Main(string[] args)
        {
            Beolvas();

            Console.WriteLine("#");
            Console.WriteLine("#");
            Console.WriteLine("#");
            Console.WriteLine("#");

            int maxert;
            maxert = leghosszabb(1) - 1 + 1;

            for (int i = 2; i <= 52; i++)
            {
                if (leghosszabb(i) - i + 1 > maxert)
                {
                    maxert = leghosszabb(i) - i + 1;
                }
            }
            Console.WriteLine(maxert);

            Console.WriteLine("#");
        }
    }
}