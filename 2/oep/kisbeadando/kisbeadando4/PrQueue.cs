using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF4
{
    internal class PrQueue
    {
        private List<Element> seq;

        public void SetEmpty()
        {
            seq = new List<Element>();
        }

        public bool isEmpty()
        {
            return seq.Count == 0;
        }

        public void Add(Element e)
        {
            seq.Add(e);
        }

        public Element GetMax()
        {
            if (isEmpty())
            {
                throw new Exception("Hiba");
            }

            (int max, int ind) = MaxSelect();

            return seq[ind];
        }

        public Element RemMax()
        {
            if (isEmpty())
            {
                throw new Exception("Hiba");
            }

            (int max, int ind) = MaxSelect();

            Element e = seq[ind];

            seq.Remove(e);

            return e;
        }

        private (int, int) MaxSelect()
        {
            if (isEmpty())
            {
                throw new Exception("Hiba");
            }

            Element max = seq.Max();

            return (max.pr, seq.IndexOf(max));
        }
    }
}
