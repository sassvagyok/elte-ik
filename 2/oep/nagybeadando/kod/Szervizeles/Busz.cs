namespace Szervizeles;

public class Busz : Jármű
{
    public Busz(string id, int gy, string öv, int új, bool köz) : base(id, gy, öv, új, köz)
    {
        
    }

    public override int OnVisit(IJárműVisitor visitor)
    {
        return visitor.Visit(this);
    }
}