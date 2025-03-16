namespace HF3
{
    public class Diag
    {
        private double[] x;

        public Diag(int n)
        {
            x = new double[n];
        }

        public double Get(int i, int j)
        {
            if (i < 0 || i >= x.Length || j < 0 || j >= x.Length)
            {
                throw new IndexOutOfRangeException();
            }

            if (i == j) return x[i];
            else return 0.0;
        }

        public void Set(int i, int j, double e)
        {
            if (i < 0 || i >= x.Length || j < 0 || j >= x.Length)
            {
                throw new IndexOutOfRangeException();
            }
            
            if(i == j) x[i] = e;
            else throw new IndexOutOfRangeException();
        }

        public static Diag Add(Diag a, Diag b)
        {
            if (a.x.Length != b.x.Length)
            {
                throw new ArgumentException();
            }

            Diag c = new Diag(a.x.Length);

            for (int i = 0; i < a.x.Length; i++)
            {
                c.x[i] = a.x[i] + b.x[i];
            }

            return c;
        }

        public static Diag Mul(Diag a, Diag b)
        {
            if (a.x.Length != b.x.Length)
            {
                throw new ArgumentException();
            }
            
            Diag c = new Diag(a.x.Length);
            
            for (int i = 0; i < a.x.Length; i++)
            {
                c.x[i] = a.x[i] * b.x[i];
            }

            return c;
        }
        
        public static Diag operator +(Diag a, Diag b)
        {
            if (a.x.Length != b.x.Length)
            {
                throw new ArgumentException();
            }

            Diag c = new Diag(a.x.Length);

            for (int i = 0; i < a.x.Length; i++)
            {
                c.x[i] = a.x[i] + b.x[i];
            }

            return c;
        }
        
        public static Diag operator *(Diag a, Diag b)
        {
            if (a.x.Length != b.x.Length)
            {
                throw new ArgumentException();
            }
            
            Diag c = new Diag(a.x.Length);
            
            for (int i = 0; i < a.x.Length; i++)
            {
                c.x[i] = a.x[i] * b.x[i];
            }

            return c;
        }
    }
}