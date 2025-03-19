namespace Gyak6.Horgaszverseny;

internal class HorgaszEnumerator: IEnumerator<Horgasz>
{
    string _path;
    StreamReader file;

    public Horgasz Current
    {
        
    }

    public bool MoveNext()
    {
        bool end = StreamReader.EndOfStream;

        if (!end)
        {
            string line = file.ReadLine()!;
            string name = line.Split(";")[0];
            int fogasSb = int.Parse(line.Split(";")[1]);

            List<Fogas> fogasok = [];

            for (int i = 0; i < fogasSb; i++)
            {
                line = file.ReadLine()!;

                Fogas fogas;
                
                string[] split = line.Split(";");
                
                fogas.Idő = split[0];
                fogas.Fajta = split[1];
                fogas.Hossz = double.Parse(split[2]);
                fogas.Súly = double.Parse(split[3]);
                
                fogasok.Add(fogas);
            }

            Current h = new Horgasz()
            {
                Nev = name,
                zsákmány = fogasok
            };
        }
        
        return end;
    }
    
    public void Reset()
    {
        file.Close();
        file = new StreamReader(_path);
    }
}