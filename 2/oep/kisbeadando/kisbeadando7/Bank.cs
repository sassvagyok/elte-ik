namespace HF7;

internal class Bank
{
    private List<Account> accounts;
    
    public Bank()
    {
        accounts = [];
    }

    public void OpenAccount(string cNum, Customer c)
    {
        var acc = new Account(cNum);

        c.AddAccount(acc);
        accounts.Add(acc);
    }

    public void ProvidesCard(string cNum)
    {
        (bool l, Account acc) = FindAccount(cNum);

        if (l)
        {
            var card = new Card(cNum, "1234");
            acc.cards.Add(card);
        }
    }
    
    public int GetBalance(string cNum)
    {
        (bool l, Account account) = FindAccount(cNum);

        if (l) return account.GetBalance();
        return -1;
    }

    public void Transaction(string cNum, int amount)
    {
        (bool l, Account account) = FindAccount(cNum);
        
        if (l) account.Change(amount);
    }
    
    public bool CheckAccount(string cNum)
    {
        (bool l, Account account) = FindAccount(cNum);

        return l;
    }
    
    private (bool, Account) FindAccount(string cNum)
    {
        for (int i = 0; i < accounts.Count; i++)
        {
            if (accounts[i].accNum == cNum)
            {
                return (true, accounts[i]);
            }
        }
        
        return (false, null);
    }
}