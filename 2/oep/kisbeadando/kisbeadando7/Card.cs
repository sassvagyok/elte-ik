namespace HF7;

internal class Card
{
    public string cNum;
    private string pin;

    public Card(string cNum, string pin)
    {
        this.cNum = cNum;
        this.pin = pin;
    }

    public bool CheckPIN(string p)
    {
        return pin == p;
    }

    public void SetPIN(string p)
    {
        pin = p;
    }
}