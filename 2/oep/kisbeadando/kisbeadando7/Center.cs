namespace HF7;

internal class Center
{
    private List<Bank> banks;

    public Center(List<Bank> b)
    {
        banks = b;
    }

    public int GetBalance(string cNum)
    {
        (bool l, Bank bank) = FindBank(cNum);

        if (l) return bank.GetBalance(cNum);

        return -1;
    }

    public void Transaction(string cNum, int amount)
    {
        (bool l, Bank bank) = FindBank(cNum);

        if (l) bank.Transaction(cNum, amount);
    }

    private (bool, Bank) FindBank(string cNum)
    {
        for (int i = 0; i < banks.Count; i++)
        {
            if (banks[i].CheckAccount(cNum))
            {
                return (true, banks[i]);
            }
        }
        
        return (false, null);
    }
}