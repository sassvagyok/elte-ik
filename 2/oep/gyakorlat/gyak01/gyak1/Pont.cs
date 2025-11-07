namespace gyak1
{
    internal class Pont // internal: csak programon belul hasznalhato
    {
        private double x;
        private double y;
        private double z;

        public Pont(double x, double y, double z) // ctor
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double Távolság(Pont q)
        {
            return Math.Sqrt(Math.Pow(x - q.x, 2) + Math.Pow(y - q.y, 2) + Math.Pow(z - q.z, 2));
        }

        public static double TávolságPont(Pont p, Pont q)
        {
            return Math.Sqrt(Math.Pow(p.x - q.x, 2) + Math.Pow(p.y - q.y, 2) + Math.Pow(p.z - q.z, 2));
        }
    }
}