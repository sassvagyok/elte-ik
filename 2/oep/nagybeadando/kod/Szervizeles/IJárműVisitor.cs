namespace Szervizeles;

public interface IJárműVisitor
{
    int Visit(Busz b);
    int Visit(Troli t);
    int Visit(Villamos v);
}