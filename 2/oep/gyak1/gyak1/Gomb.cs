namespace gyak1
{
    internal class Gomb
    {
        private Pont c;
        private double r;

        public Gomb(Pont p, double a)
        {
            if (a < 0)
            {
                throw new ArgumentException("Radius cannot be less than 0");
            }
            
            c = p;
            r = a;
        }

        public bool Tartalmaz(Pont p)
        {
            return c.Távolság(p) <= r;
        }
    }
}