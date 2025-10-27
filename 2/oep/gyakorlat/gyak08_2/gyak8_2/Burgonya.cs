namespace gyak8_2;

internal class Burgonya : Zöldség
{
    private static Burgonya? _instance;
    
    public static Burgonya Instance
    {
        get
        {
            if (_instance is null)
            {
                _instance = new Burgonya(10);
            }
            
            return _instance;
        }
    }
    
    private Burgonya(int i) : base(i)
    {
        
    }
}