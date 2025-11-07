/*
 * Pőcze Máté
 * EBYPPB
 * nevsor novekvo sorrnendben
 */

using System;

namespace gyak5
{
    internal class Program
    {
        struct Tanulo
        {
            public string nev;
            public int magassag;

            public Tanulo(string sor)
            {
                string[] adatok = sor.Split(' ');
                nev = adatok[0];
                magassag = int.Parse(adatok[1]);
            }
        }
        static void Main(string[] args)
        {
            // deklaracio
            int n;
            Tanulo[] tanulok;
            bool monoton;

            // beolvasaas
            n = int.Parse(Console.ReadLine()+ ""); // igy nem huzza ala
            tanulok = new Tanulo[n];
            
            for (int j = 0; j < tanulok.Length; j++)
            {
                tanulok[j] = new Tanulo(Console.ReadLine()+ "");
            }
            
            // feldolgozas
            int i = 0;

            while (i < n - 1 && tanulok[i].magassag <= tanulok[i + 1].magassag)
            {
                i++;
            }

            monoton = i == n - 1;
            
            // kiiras
            
            Console.Error.Write("Az eredmény: ");
            if (monoton)
            {
                Console.WriteLine("IGEN");
            }
            else
            {
                Console.WriteLine("NEM");
            }
        }
    }
}