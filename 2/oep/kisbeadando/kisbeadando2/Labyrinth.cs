using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF2
{
    internal class Labyrinth
    {
        private int n;
        private int m;
        private Content[,] map;

        public Labyrinth(Content[,] map)
        {
            this.n = map.GetLength(0);
            this.m = map.GetLength(1);
            this.map = map;
        }

        public Content LookAt(int x, int y, Direction dir)
        {
            if (x + dir.x < 0 || y + dir.y < 0 || x + dir.x >= n || y + dir.y >= m)
            {
                throw new ArgumentException("Rossz bemenet");
            }

            return this.map[x + dir.x, y + dir.y];
        }

        public void Collect(int x, int y)
        {
            if (this.map[x, y] != Content.TREASURE)
            {
                throw new ArgumentException("Rossz bemenet");
            }

            this.map[x, y] = Content.EMPTY;
        }
    }
}
