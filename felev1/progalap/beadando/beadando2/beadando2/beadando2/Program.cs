/*
 * Pőcze Máté
 * EBYPPB
 * ebyppb@inf.elte.hu
 *
 * Feladat: Drága beszerzések
 */

using System;
using System.Collections.Generic;

namespace beadando2
{
    internal class Program
    {
        static int n;
        static Termek[] t;

        struct Termek
        {
            public int ar;
            public int elad;
        }
        
        static void beolvas()
        {
            string bemenet;
            string[] darabok;

            bemenet = Console.ReadLine();
            n = int.Parse(bemenet);

            t = new Termek[n + 1];

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

                t[i].ar = int.Parse(darabok[0]);
                t[i].elad = int.Parse(darabok[1]);
            }
        }

        static double elemekKozepe()
        {
            double s = 0;

            for (int j = 1; j <= n; j++)
            {
                s += t[j].elad;
            }
            
            return s / 2;
        }

        static int osszEladott(int i)
        {
            int s = 0;

            for (int j = 1; j <= i; j++)
            {
                s += t[j].elad;
            }

            return s;
        }
        
        static void Main(string[] args)
        {
            // Beolvasás
            beolvas();
            
            // Deklaráció
            int legnagyobbAr = 0;
            bool van = false;

            // Feldolgozás
            for (int i = 1; i <= n; i++)
            {
                if (elemekKozepe() <= osszEladott(i) && !van)
                {
                    legnagyobbAr = t[i].ar;
                    van = true;
                }
            }
            
            // Kiírás
            Console.WriteLine(legnagyobbAr);
        }
    }  
}