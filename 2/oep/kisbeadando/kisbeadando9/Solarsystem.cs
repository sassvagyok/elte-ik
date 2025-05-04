namespace HF9;

internal class Solarsystem
{
    public List<Planet> planets;

    public Solarsystem()
    {
        planets = new List<Planet>();
    }
    
    public (bool, Starship) MaxFireP()
    {
        bool boolMaxFire = false;
        double maxFire = double.MinValue;
        Starship s = null;
        
        foreach (Planet p in planets)
        {
            if (p.MaxFireP().Item2 > maxFire)
            {
                boolMaxFire = true;
                maxFire = p.MaxFireP().Item2;
                s = p.MaxFireP().Item3;
            }
        }

        return (boolMaxFire, s);
    }

    public List<Planet> Defenseless()
    {
        List<Planet> defenselessPlanets = new List<Planet>();
        
        foreach (Planet p in planets)
        {
            if (p.ShipCount() == 0)
            {
                defenselessPlanets.Add(p);
            }
        }
        
        return defenselessPlanets;
    }
}