namespace gyak3
{
    internal class Program
    {
        struct File
        {
            public string nev;
            public int meret;
            public int ido;
        }
        static void Main(string[] args)
        {
            //string[] napok = new string[10]; // [0..9]

            string[] napok = ["", "hétfő", "kedd", "szerda", "csütörtök", "péntek", "szombat", "vasárnap"];

            int k;
            string bemenet;

            bemenet = Console.ReadLine();
            while(!int.TryParse(bemenet, out k) || k < 1 || k > 31)
            {
                bemenet = Console.ReadLine();
            }

            string nap;
            if (k % 7 == 0)
            {
                nap = napok[0];
            }
            else
            {
                nap = napok[k % 7];
            }

            Console.WriteLine(nap);


            //int n = napok.Length;


            int n;
            bemenet = Console.ReadLine();
            n = int.Parse(bemenet);

            int[] x = new int[n + 1];

            //1. szamok egymas alatt vannak

            for (int i = 1; i <= n; i++)
            {
                bemenet = Console.ReadLine();
                x[i] = int.Parse(bemenet);
            }

            for (int i = 1; i <= n; i++)
            {
                Console.WriteLine(x[i]*2);
            }

            //2. ha egymas mellett vannak
            bemenet = Console.ReadLine();

            string[] darabok;
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            for(int i = 1; i <= n; ++i)
            {
                x[i] = int.Parse(darabok[i - 1]);
            }

            bemenet = Console.ReadLine();
            darabok = bemenet.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            File file;

            while(darabok.Length != 3 || !int.TryParse(darabok[1], out file.meret) || !int.TryParse(darabok[2], out file.ido) || file.meret < 0 || file.ido < 0)
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
