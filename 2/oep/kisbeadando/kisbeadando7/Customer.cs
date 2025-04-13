namespace HF7;

internal class Customer
{
    private string pin;
    private int withdraw;
    private List<Account> accounts;

    public Customer(string p, int w)
    {
        pin = p;
        withdraw = w;
        accounts = [];
    }

    public void Withdrawal(ATM atm)
    {
        atm.Process(this);
    }

    public Card ProvidesCard()
    {
        return accounts[0].cards[0];
    }

    public string ProvidesPIN()
    {
        return pin;
    }

    public int RequestMoney()
    {
        return withdraw;
    }

    public void AddAccount(Account a)
    {
        accounts.Add(a);
    }
}