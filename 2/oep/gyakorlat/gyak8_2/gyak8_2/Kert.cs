namespace gyak8_2;

internal class Kert
{
    private List<Parcella> parcellák;

    public Kert(List<Parcella> ps)
    {
        
    }

    public void Ültet(int hova, Növényfajta mit)
    {
        parcellák[hova].Ültet(mit);
    }

    public void Arat(int hol)
    {
        parcellák[hol].Arat();
    }

    // public List<int> Aratható(int hónap)
    // {
    //     List<int> parcellák = [];
    //
    //     for (int i = 0; i < parcellák.Count; i++)
    //     {
    //         if 
    //     }
    // }
}