namespace gyak8_2;

class Program
{
    static void Main(string[] args)
    {
        var növény = Burgonya.Instance;
        
        Console.WriteLine(növény.IsZöldség());
        Console.WriteLine(növény is Zöldség);
        // a kettő ugyanazt csinálja
    }
}