package servPattern;

public class DemandeManageGame implements IDemandeManageGame{

    @Override
    public Grid demandeGrid(IGrid g) {
        return null;
    }

    @Override
    public String demandeColor(IGrid g) {
        g.assignColor();
        return null;
    }

    @Override
    public String demandeTurn(IGrid g) {
        g.playerTurn();
        return null;
    }
}
