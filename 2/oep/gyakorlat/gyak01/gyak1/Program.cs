namespace gyak1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Pont p1 = new Pont(0, 0, 0);
            Pont p2 = new Pont(0, 0, 1);
            
            Console.WriteLine(p1.Távolság(p2));

            Gomb g = new Gomb(p1, 1);
            Console.WriteLine(g.Tartalmaz(p1));
            
            Pont p3 = new Pont(1, 1, 1);
            Console.WriteLine(g.Tartalmaz(p3));

            //Gomb g2 = new Gomb(p1, -3);
            
            Console.WriteLine(Max([1,0,4,5], new Polinom(1,0,0)));

            double[] a = [1, 0, 4, 5];
            var pol = new Polinom(1, 2, -5);
            
            Console.WriteLine(a.All(x => pol.Value(x) > 0));
            
            var v1 = new Vector(1, 0);
            var v2 = new Vector(0, 1);

            Vector v3 = v1 + v2;
            double skalar = v1 * v2;

        }

        private static int Max(double[] v, Polinom p)
        {
            double max = p.Value(v[0]);
            int maxInd = 0;

            for (int i = 1; i < v.Length; i++)
            {
                if (p.Value(v[i]) > max)
                {
                    maxInd = i;
                    max = p.Value(v[i]);
                }
            }
            
            return maxInd;
        }
    }   
}