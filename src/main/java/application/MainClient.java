package application;

import javafx.application.Application;

/**
 * Contient la méthode main()
 *
 */
public class MainClient {

    /**
     * Méthode principale : lance le programme
     *
     * @param args
     */
    public static void main(String[] args) {
        // Instancie l'interface graphique du client
        Application.launch(GameApplication.class, args);
        System.out.println("attente interface graphique");
    }

}