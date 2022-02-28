package client;

public class Player implements IPlayer{

    private final int port = 6666;
    public final ClientTCP monClientTCP;
    private final String color;

    public Player(int id) {
        monClientTCP = new ClientTCP("localhost", port);

        if (id % 2 == 0) {
            this.color = "red";
        }
        else {
            this.color = "yellow";
        }
    }

    public String getColor() {
        return color;
    }


    @Override
    public boolean connexionGrid() {
        return monClientTCP.connecterAuServeur();
    }

    @Override
    public void deconnexionGrid() {
        monClientTCP.deconnecterDuServeur();
    }

    @Override
    public void play(int position) {

    }

    @Override
    public int demandToPlay(int position) {
        int new_position = 0;
        String wantedPosition = monClientTCP.transmettreChaine("jouer " + position);
        new_position = Integer.parseInt(wantedPosition);
        play(new_position);
        return new_position;

    }
}
