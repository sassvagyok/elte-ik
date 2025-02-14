using System; // kell a mesternek/bírónak

namespace gyak
{ //kell kapcsos a masternek/bírónak
    internal class gyak4
    {
        static void Main(string[] args)
        {
            string bemenet;
            int n;
            
            bemenet = Console.ReadLine();
            n = int.Parse(bemenet);

            int[] h = new int[n + 1];

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                h[i] = int.Parse(bemenet);
            }

            int maxInd, maxert;

            maxert = h[1];
            maxInd = 1;

            for (int i = 2; i <= n; i++)
            {
                if (h[i] > maxert)
                {
                    maxert = h[i];
                    maxInd = i;
                }
            }
            
            Console.Error.WriteLine(maxInd); //ezt nem veszi figyelembe a biro/mester
            Console.WriteLine(maxInd);
        }
    }
}