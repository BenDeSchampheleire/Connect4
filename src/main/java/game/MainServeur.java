package game;

/**
 * Contient la méthode main()
 *
 */
public class MainServeur {

    /**
     * Méthode principale : lance le programme
     *
     * @param args
     */
    public static void main(String[] args) {

        Grid grid = new Grid(6, 6);
        System.out.println("Creation de la grille: " + grid);


        // Lancement de la partie
        grid.startGame();
    }

}

