namespace gyak5;

record Kaktusz
{
    public required string Nev { get; init; }
    public required string Szin { get; init; }
    public required string Os { get; init; }
    public int Méret { get; init; }

    public static Kaktusz Parse(string line)
    {
        var parts = line.Split(';');
        return new Kaktusz
        {
            Nev = parts[0],
            Szin = parts[1],
            Os = parts[2],
            Méret = int.Parse(parts[3])
        };
    }
}
