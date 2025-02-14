namespace gyak1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
             * Console.WriteLine("Hello, World!"); ures sor a vegen
             * Console.Write("asd"); nincs ures sor a vegen
             * String bemenet = Console.ReadLine(); beolvasás
             * int, long - egészek
             * float, double - 'valósak'
             * bool - logikai ertek
             * char - karakter; csak: ''
             * string - szöveg; csak ""
             */

            /*
             * if(feltetel)
             * {
             *      ha teljesul mi tortenjen
             * } 
             * else if(masik feltetel)
             * {
             *      ha a masik teljesul
             * } 
             * else
             * {
             *      ha nem teljesul a feltetel mi tortenjeb
             * }
             */
            /*
             * && - és
             * || - vagy
             * ! - tagadás
             * !(d < 0 || s <= 0)
             */

            string bemenet;
            double d, s;

            Console.WriteLine("Távolság:");
            bemenet = Console.ReadLine();
            d = double.Parse(bemenet);

            Console.WriteLine("Idő:");
            bemenet = Console.ReadLine();
            s = double.Parse(bemenet);

            if(d >= 0 && s > 0)
            {
                double v;
                v = d / s;
                Console.WriteLine("Sebesség:" + v);
            }
            else
            {
                Console.WriteLine("Nem jó a bemenet :(");
            }

            double c, f;

            Console.WriteLine("Celsius: ");
            bemenet = Console.ReadLine();
            c = double.Parse(bemenet);

            if(c >= -273.15)
            {
                f = c * 1.8 + 32;
                Console.WriteLine("Fahrenheit: " + f);
            } 
            else
            {
                Console.WriteLine("Nem jó a bemenet!");
            }
            
        }
    }
}
