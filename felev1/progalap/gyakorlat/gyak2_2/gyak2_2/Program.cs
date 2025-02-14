namespace gyak2_2
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string bemenet;
            int x, y;
            bemenet=Console.ReadLine();

            while(!(int.TryParse(bemenet, out x) && x >= 0))
            {
                Console.WriteLine("Nem jó a bemenet");
                bemenet=Console.ReadLine();
            }
            bemenet = Console.ReadLine();

            while (!(int.TryParse(bemenet, out y) && y > 0))
            {
                Console.WriteLine("Nem jó a bemenet");
                bemenet = Console.ReadLine();
            }

            while(x >= y)
            {
                x = x - y;
            }

            int m;
            m = x;
            Console.WriteLine(m);
        }
    }
}
