package game;

public interface IGrid {

    void play_checker(int column_number, String color);

    void startGame();

    //String assignColor();

    String playerTurn();

}
