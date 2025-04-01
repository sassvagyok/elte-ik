namespace gyak8_2;

internal abstract class Virág : Növényfajta
{
    protected Virág(int i) : base(i)
    {
        
    }

    public override bool IsVirág()
    {
        return true;
    }
}