namespace kisbeadando5;

internal class Program
{
    static void Main(String[] args)
    {
        double sum = 0.0;
        int db = 0;
        
        using StreamReader st = new StreamReader(args[0]);

        if (st.EndOfStream) return;
        
        int e = int.Parse(st.ReadLine());

        while (!st.EndOfStream && e >= 0)
        {
            sum += e;
            db++;
            
            e = int.Parse(st.ReadLine());
        }
        
        double átl = sum / db;

        bool l = true;
        int min = e;

        while (!st.EndOfStream)
        {
            e = int.Parse(st.ReadLine());
            
            l = l && e < 0;

            if (e < min)
            {
                min = e;
            }
        }
        
        Console.WriteLine(átl);
        Console.WriteLine(l);
        Console.WriteLine(min);
    }
}