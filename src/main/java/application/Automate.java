package application;

import game.Checker;
import game.Grid;

import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Automate implements IAutomate{

    private String color;
    public ClientTCP monClientTCP; // ## link monClientTCP
    private final PropertyChangeSupport notifier;

    public Automate(ClientTCP unClient) {

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
        return monClientTCP.connexionServeur();
    }

    @Override
    public void deconnexionGame() {
        monClientTCP.deconnexionServeur();
    }

    public void demandePlay(int columnNumber, String color, Checker checker) {

        System.out.println("****** demande play ********");

        if (Objects.equals(color, "red")) {
            monClientTCP.transmettreChaine("red " + columnNumber);
        }
        else {
            monClientTCP.transmettreChaine("yellow " + columnNumber);
        }

        //notifier.firePropertyChange("play", checker, color);
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
