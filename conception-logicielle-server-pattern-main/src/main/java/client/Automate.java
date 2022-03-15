package client;

import servPattern.Checker;
import servPattern.Grid;

import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Automate implements IAutomate{

    private String color;
    public ClientTCP monClientTCP; // ## link monClientTCP
    private final PropertyChangeSupport notifier;
    // each player has its own grid
    private Grid grid;

    public Automate(ClientTCP unClient) {

        this.connexionGame();
        // its own grid
        this.grid = this.demandeGrid();
        this.deconnexionGame();

        this.color = "white";

        monClientTCP = unClient;

        notifier = new PropertyChangeSupport(this);

        System.out.println("Couleur automate " + getColor());
    }


    public void setColor(String color) {this.color = color;}

    public String getColor() {return color;}

    public PropertyChangeSupport getNotifier() {
        return notifier;
    }

    @Override
    public boolean connexionGame() {
        return monClientTCP.connecterAuServeur();
    }

    @Override
    public void deconnexionGame() {
        monClientTCP.deconnecterDuServeur();
    }

    public void demandePlay(int columnNumber, String color, Checker checker) {

        // play on its own grid
        this.grid.play_checker(columnNumber, color);

        System.out.println("****** demande play ********");

        if (Objects.equals(color, "red")) {
            monClientTCP.transmettreChaine("red " + columnNumber);
        }
        else {
            monClientTCP.transmettreChaine("yellow " + columnNumber);
        }

        // notify the controller that it has to update the UI
        notifier.firePropertyChange("iPlayed", checker, color);
        //play(laSomme);

    }

    public String demandeColor() {
        String color;
        System.out.println("****** demande color ********");

        color = monClientTCP.transmettreChaine("GiveMeAColor");
        setColor(color);
        System.out.println("colorAssigned: " + color);
        return color;
    }

    public boolean demandeTurn() {
        boolean iPlay;
        String color;
        System.out.println("****** demande turn ********");

        color = monClientTCP.transmettreChaine("MyTurnToPlay?");

        // true if it is his turn to play, false otherwise
        iPlay = Objects.equals(color, this.getColor());

        return iPlay;
    }

    public Grid demandeGrid() {
        Grid grid;
        System.out.println("****** demande grid ********");

        grid = monClientTCP.transmettreChaineGrid("GiveMeTheGrid");

        return grid;
    }
}
