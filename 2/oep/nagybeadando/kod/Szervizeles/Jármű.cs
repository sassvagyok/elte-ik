namespace Szervizeles;

public abstract class Jármű
{
    private string _id;
    private int _gyév;
    private string _övezet;
    private int _újár;
    public bool közlekedik;
    public List<Munkalap> munkafolyamatok;
    
    public string id
    {
        get => _id;
    }
    public int gyév
    {
        get => _gyév;
    }
    
    public string övezet
    {
        get => _övezet;
    }
    
    public int újár
    {
        get => _újár;
    }

    public Jármű(string id, int gy, string öv, int új, bool köz)
    {
        _id = id;
        _gyév = gy;
        _övezet = öv;
        _újár = új;
        közlekedik = köz;
        munkafolyamatok = new List<Munkalap>();
    }

    public abstract int OnVisit(IJárműVisitor visitor);

    public virtual int AkutálisÉrték()
    {
        return OnVisit(new AktuálisÉrtékVisitor());
    }
}