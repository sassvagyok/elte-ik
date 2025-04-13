namespace HF7;

internal class Account
{
    public string accNum;
    public List<Card> cards;
    private int balance;

    public Account(string cNum)
    {
        accNum = cNum;
        balance = 0;
        cards = [];
    }

    public int GetBalance()
    {
        return balance;
    }

    public void Change(int a)
    {
        balance += a;
    }
}