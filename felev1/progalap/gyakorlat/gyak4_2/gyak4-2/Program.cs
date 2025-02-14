using System;
namespace gyak4_2
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string bemenet;
            int k;

            bemenet = Console.ReadLine();
            
            k = int.Parse(bemenet);
            
            int[] x = new int[k + 1];

            for (int i = 1; i <= k; i++)
            {
                bemenet = Console.ReadLine();
                x[i] = int.Parse(bemenet);
            }

            int s = 0;

            for (int i = 1; i <= k; i++)
            {
                s += x[i] * x[i];
            }
            
            Console.WriteLine(s);
        }
    }
}