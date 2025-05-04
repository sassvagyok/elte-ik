namespace HF9;

internal class Planet
{
    public string name;
    
    private List<Starship> ships = new List<Starship>();

    public Planet(string name)
    {
        this.name = name;
    }

    public void Defends(Starship s)
    {
        if (ships.Contains(s))
        {
            throw new InvalidOperationException();
        }
        
        ships.Add(s);
    }

    public void Leaves(Starship s)
    {
        if (!ships.Contains(s))
        {
            throw new InvalidOperationException();
        }
        
        ships.Remove(s);
    }

    public int ShipCount()
    {
        return ships.Count;
    }

    public int ShieldSum()
    {
        int count = 0;

        foreach (Starship s in ships)
        {
            count += s.GetShield();
        }
        
        return count;
    }

    public (bool, double, Starship) MaxFireP()
    {
        bool boolMaxFire = false;
        double maxFire = double.MinValue;
        Starship s = null;

        foreach (Starship ship in ships)
        {
            if (ship.FireP() > maxFire)
            {
                boolMaxFire = true;
                maxFire = ship.FireP();
                s = ship;
            }
        }
        
        return (boolMaxFire, maxFire, s);
    }
}