namespace gyak8_2;

internal abstract class Zöldség : Növényfajta
{
    protected Zöldség(int i) : base(i)
    {
        
    }

    public override bool IsZöldség()
    {
        return true;
    }
}