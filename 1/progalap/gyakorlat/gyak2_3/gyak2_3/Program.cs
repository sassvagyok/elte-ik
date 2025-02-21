namespace gyak2_3
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string bemenet;
            int n;

            bemenet = Console.ReadLine();

            while(!(int.TryParse(bemenet, out n) && 1 <= n && n <= 30))
            {
                bemenet = Console.ReadLine();
            }

            string[] nevek = new string[n + 1];

            /*
             * for(int i = e; i <= v; i++)
             * 
             */
            for(int i = 1; i <= n; i++)
            {
                nevek[i] = Console.ReadLine();
            }
        }
    }
}
