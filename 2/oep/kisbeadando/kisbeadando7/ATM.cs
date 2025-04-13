namespace HF7;

internal class ATM
{
    public string site;
    public Center center;

    public ATM(string site, Center c)
    {
        this.site = site;
        center = c;
    }

    public void Process(Customer c)
    {
        Card card = c.ProvidesCard();

        if (card.CheckPIN(c.ProvidesPIN()))
        {
            int a = c.RequestMoney();

            if (center.GetBalance(card.cNum) >= a)
            {
                center.Transaction(card.cNum, -a);
            }
        }
    }
}