/*
Pőcze Máté
EBYPPB
ebyppb@inf.elte.hu

Feladat: Legvastagabb jég a Balatonon - Maximumkiválasztás
*/

using System;

namespace beadando1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            
            // Deklacárió
            int n;
            string bemenet;

            // Beolvasás
            bemenet = Console.ReadLine();
            
            n = int.Parse(bemenet);
            
            int[] k = new int[n + 1];

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                k[i] = int.Parse(bemenet);
            }

            // Feldolgozás
            int maxInd, maxErt;
            
            maxErt = k[1];
            maxInd = 1;

            for (int i = 2; i <= n; i++)
            {
                if (k[i] > maxErt)
                {
                    maxErt = k[i];
                    maxInd = i;
                }
            }
            
            // Kiírás
            Console.WriteLine(maxInd);
        }
    }  
}