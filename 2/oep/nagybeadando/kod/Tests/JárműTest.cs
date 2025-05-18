using Szervizeles;

namespace Tests;

[TestClass]
public class JárműTest
{
    private Önkormányzat ok;
    private List<Munkalap> munkalapok = new List<Munkalap>();
    private List<Munkafolyamat> munkak = new List<Munkafolyamat>();
    
    [TestInitialize]
    public void Initialize()
    {
        StreamReader sr = new StreamReader("test1.txt");

        List<Jármű> járművek = new List<Jármű>();
        List<Szerviz> szervizek = new List<Szerviz>();

        while (!sr.EndOfStream)
        {
            string line = sr.ReadLine();
            var parts = line.Split(';');
            
            Jármű j;

            if (parts[0] == "BUSZ")
            {
                j = new Busz(parts[1], int.Parse(parts[2]), parts[3], int.Parse(parts[4]), bool.Parse(parts[5]));
            
                járművek.Add(j);
            } 
            else if (parts[0] == "VILLAMOS")
            {
                j = new Villamos(parts[1], int.Parse(parts[2]), parts[3], int.Parse(parts[4]), bool.Parse(parts[5]));
            
                járművek.Add(j);
            }
            else if (parts[0] == "TROLI")
            {
                j = new Troli(parts[1], int.Parse(parts[2]), parts[3], int.Parse(parts[4]), bool.Parse(parts[5]));
            
                járművek.Add(j);
            }

            if (parts[0] == "SZERVIZ")
            {
                Szerviz s = new Szerviz(parts[1]);
                szervizek.Add(s);
            }

            if (parts[0] == "MUNKALAP")
            {
                j = járművek.FirstOrDefault(j => j.id == parts[2]);
                Munkalap m = new Munkalap(szervizek.Find(x => x.szerviz == parts[1]), j, bool.Parse(parts[3]));
            
                munkalapok.Add(m);
            }
            
            if (parts[0] == "MUNKAFOLYAMAT")
            {
                j = járművek.FirstOrDefault(j => j.id == parts[1]);
                Munkafolyamat mf = new Munkafolyamat(j, parts[2], int.Parse(parts[3]));
            
                munkak.Add(mf);
            }
        }
        
        sr.Close();
        
        ok = new Önkormányzat(járművek, szervizek);
    }
    
    [TestMethod]
    public void TestAktuálisÉrték()
    {
        List<int> expected = new List<int>()
        {
            2325000,
            1225806,
            2050327,
            1360000,
            1322581,
            912000,
            1620000,
            1720000
        };

        for (int i = 0; i < ok.jarművek.Count; i++)
        {
            Assert.AreEqual(expected[i], ok.jarművek[i].AkutálisÉrték());
        }
    }
}