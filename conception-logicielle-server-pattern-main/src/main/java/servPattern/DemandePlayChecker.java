package servPattern;

public class DemandePlayChecker implements IDemandePlayChecker{

    @Override
    public int demandePlay(int columnNumber, String color, IGrid grid) {
        grid.play_checker(columnNumber, color);
        return 0;
    }
}
