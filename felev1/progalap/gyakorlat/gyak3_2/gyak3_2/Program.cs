namespace gyak3
{
    internal class Program
    {
        struct Ido
        {
            public int ora1;
            public int perc1;
            public int ora2;
            public int perc2;
        }
        static void Main(string[] args)
        {
            string bemenet;
            string[] darabok;

            bemenet = Console.ReadLine();
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            Ido ido;

            while (darabok.Length != 3 || !int.TryParse(darabok[1], out file.meret) || !int.TryParse(darabok[2], out file.ido) || file.meret < 0 || file.ido < 0)
            {
                bemenet = Console.ReadLine();
                darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);
            }
            //file.nev = darabok[0];
            //file.meret = int.Parse(darabok[1]);
            //file.ido = int.Parse(darabok[2]);

            bemenet = Console.ReadLine();

            int most;

            while (!int.TryParse(bemenet, out most) || most < file.ido)
            {
                bemenet = Console.ReadLine();
            }

            int kul;
            kul = most - file.ido;
            Console.WriteLine(kul);
        }
    }
}
