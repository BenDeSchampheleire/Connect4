package servPattern;
import client.Player;
import servPattern.Grid;
import java.beans.PropertyChangeSupport;

public class Game implements IGame{

    private String turn;
    public Grid grid;
    public Player player1;
    public Player player2;

    private IDemandeJouer strategyjouer;

    /**
     * Support du mécanisme Observable/Observer
     */
    private final PropertyChangeSupport pcSupport;

    public Game(Grid grid, Player player1, Player player2) {
        setTurn("red");
        this.grid = grid;
        this.player1 = player1;
        this.player2 = player2;
        pcSupport = new PropertyChangeSupport(this);
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public boolean check_win(String color){
        return this.grid.checkWin(color);
    }

    public void play(int column_number, String color) {

        this.grid.play_checker(column_number, color);
        pcSupport.firePropertyChange();
    }

    @Override
    public synchronized int demandeJouer(String color, int position) {
        return strategyjouer.demandeJouer(color, position, this);
    }
}
