using System.Collections;
using Gyak6.Horgaszverseny;

namespace Gyak6;

internal class HorgaszFile : IEnumerable<Horgasz>
{
    private readonly string _path;

    public HorgaszFile(string path)
    {
        _path = path;
    }

    public IEnumerator<Horgasz> GetEnumerator()
    {
        return new HorgaszEnumerator(_path);
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}