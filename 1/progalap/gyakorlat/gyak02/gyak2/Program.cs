namespace gyak2
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string bemenet;
            double a, b, c;
            bemenet = Console.ReadLine();

            /*
             * double.TryParse(szöveg, out változó)
             *  -> true
             *  -> false
             */

            while(!(double.TryParse(bemenet, out a) && a > 0))
            {
                Console.WriteLine("Adj meg egy helyes bemenetet:");
                bemenet = Console.ReadLine();
            }
            bemenet=Console.ReadLine();

            while(!(double.TryParse(bemenet, out b) && b > 0))
            {
                Console.WriteLine("Adj meg egy helyes bemenetet:");
                bemenet = Console.ReadLine();
            }
            bemenet = Console.ReadLine();

            while (!(double.TryParse(bemenet, out c) && c > 0))
            {
                Console.WriteLine("Adj meg egy helyes bemenetet:");
                bemenet = Console.ReadLine();
            }

            bool dr;

            if(a * a == b * b + c * c || b * b == a * a + c * c || c * c == a * a + b * b)
            {
                dr = true;
            }
            else
            {
                dr = false;
            }

            if(dr)
            {
                Console.WriteLine("derékszögű");
            }
            else
            {
                Console.WriteLine("nem derékszögű");
            }
        }
    }
}