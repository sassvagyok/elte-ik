namespace gyak2;

class Program
{
    static void Main(string[] args)
    {
        // Labirintus l = new Labirintus(3, 3);
        //
        // l.Kémlel(new Pont(1, 1), new Pont(1, 0));
        
        // Console.WriteLine(new Rac(3, 4));
        // Console.WriteLine(new Rac(-1, 2));
        // Console.WriteLine(new Rac(1, -2));
        // Console.WriteLine(new Rac(-1, -2));

        Rac? x = null;
        do
        {
            Console.Write("n: ");
            int n = int.Parse(Console.ReadLine());
            Console.Write("d: ");
            int d = int.Parse(Console.ReadLine());

            try
            {
                x = new Rac(n, d);
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("Division by zero");
            }
            catch (Exception)
            { }
        } while (x is null);
        
        Console.WriteLine(x);
    }
}