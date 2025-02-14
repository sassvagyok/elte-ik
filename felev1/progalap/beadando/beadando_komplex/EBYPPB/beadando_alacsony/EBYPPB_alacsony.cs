//#define BIRO
using System;
using System.Collections.Generic;

namespace beadando_komplex
{
    internal class EBYPPB_alacsony
    {
        static int n, m;
        static int[,] h;

        static void beolvas()
        {
            string bemenet;
            string[] darabok;
            
#if BIRO
            bemenet = Console.ReadLine();
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            n = int.Parse(darabok[0]);
            m = int.Parse(darabok[1]);
            
            h = new int[n + 1, m + 1];

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

                for (int j = 1; j <= m; j++)
                {
                    h[i, j] = int.Parse(darabok[j - 1]);
                }
            }
        
#else

            bool hiba;

            do
            {
                Console.ResetColor();
                Console.Write("Települések száma: ");
                
                bemenet = Console.ReadLine();
                
                hiba = !int.TryParse(bemenet, out n) || n < 1 || n > 1000;

                if (hiba)
                {
                    Console.ForegroundColor = ConsoleColor.DarkRed;
                    Console.WriteLine("Helytelen formátum! (1-1000)");
                }
            } while(hiba);
            
            do
            {
                Console.ResetColor();
                Console.Write("Napok száma: ");
                
                bemenet = Console.ReadLine();
                
                hiba = !int.TryParse(bemenet, out m) || m < 1 || m > 1000;

                if (hiba)
                {
                    Console.ForegroundColor = ConsoleColor.DarkRed;
                    Console.WriteLine("Helytelen formátum! (1-1000)");
                }
            } while(hiba);
            
            h = new int[n + 1, m + 1];
            
            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= m; j++)
                {
                    do
                    {
                        Console.ResetColor();
                        Console.Write($"{i}. település {j}. napi hőmérséklete: ");
                    
                        bemenet = Console.ReadLine();
                        
                        hiba = !int.TryParse(bemenet, out h[i, j]) || h[i, j] < -50 || h[i, j] > 50;
                            
                        if (hiba)
                        {
                            Console.ForegroundColor = ConsoleColor.DarkRed;
                            Console.WriteLine("Helytelen formátum! (-50-50)");
                        }
                    } while (hiba);
                }
            }
#endif
        }

        static bool melegebb(int j)
        {
            int i = 1;
            while (i <= n && h[i, j] > h[i, j - 1])
            {
                i++;
            }

            return i > n;
        }

        static (int Darab, List<int> Napok) feldolgozas()
        {
            int k = 0;
            List<int> napok = new List<int>();

            for (int i = 2; i <= m; i++)
            {
                if (melegebb(i))
                {
                    k++;
                    napok.Add(i);
                }
            }
            
            return (k, napok);
        }

        static void kiir((int Darab, List<int> Napok) t)
        {
#if BIRO
            Console.WriteLine(t.Darab + " " + string.Join(" ", t.Napok));
#else
            if (t.Darab > 1)
            {
                Console.ForegroundColor = ConsoleColor.DarkGreen;
                Console.WriteLine($"{t.Darab} napon volt melegebb, ezeken a napokon: {string.Join(", ", t.Napok)}");
            }
            else if (t.Darab == 1)
            {
                Console.ForegroundColor = ConsoleColor.DarkGreen;
                Console.WriteLine($"{t.Darab} napon volt melegebb, ezen a napon: {string.Join(", ", t.Napok)}"); 
            }
            else
            {
                Console.ForegroundColor = ConsoleColor.DarkGreen;
                Console.WriteLine("Nem volt egyszer sem, hogy mindenhol melegebb volt, mint az előző nap!");
            }
            Console.BackgroundColor = ConsoleColor.White;
            Console.ForegroundColor = ConsoleColor.Black;
            Console.Write("Kérem, nyomjon ENTER-t a folytatáshoz!");
            Console.ResetColor();
            Console.ReadLine(); // hogy ne zárja be rögtön a program magát
#endif
        }
        
        static void Main(string[] args)
        {
            beolvas();
            kiir(feldolgozas());
        }
    }
}