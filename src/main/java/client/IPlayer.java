package client;

public interface IPlayer {

    boolean connexionGrid();

    void deconnexionGrid();

    public void play(int position);

    public int demandToPlay(int position);

}
