namespace gyak7;

class Program
{
    static void Main(string[] args)
    {
        Részleg műszaki = new([
                new Termék("sajt", 1000),
                new Termék("tej", 500),
                new Termék("sajt", 800),
                new Termék("tojás", 450)
        ]);
        Részleg élelmiszer = new([]);
        
        Üzlet u = new Üzlet(élelmiszer, műszaki);

        Vásárló v = new Vásárló(["sajt", "tojás"]);
        v.Vásárol(u);
        
        Console.WriteLine(string.Join(", ", v.kosár.Select(x => x.Név)));
    }
}