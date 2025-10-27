namespace gyak8_2;

internal abstract class Növényfajta
{
    public int Érésiidő { get; private set; }

    protected Növényfajta(int i)
    {
        Érésiidő = i;
    }

    public virtual bool IsZöldség()
    {
        return false;
    }

    public virtual bool IsVirág()
    {
        return false;
    }
}