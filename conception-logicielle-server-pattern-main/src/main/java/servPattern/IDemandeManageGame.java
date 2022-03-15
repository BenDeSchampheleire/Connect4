package servPattern;


public interface IDemandeManageGame {

    public Grid demandeGrid(IGrid g);

    public String demandeColor(IGrid g);

    public String demandeTurn(IGrid g);
}
