namespace gyak7;

internal class Részleg
{
    public List<Termék> Készlet;

    public Részleg(List<Termék> keszlet)
    {
        Készlet = new List<Termék>(keszlet); // vagy [..keszlet]
    }
}