package servPattern;

public interface IGrid extends IContext{

    void play_checker(int column_number, String color);

    void startGame();

    String assignColor();

    String playerTurn();





}
