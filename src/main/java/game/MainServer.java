package game;


public class MainServer {

    public static void main(String[] args) {

        Grid grid = new Grid(7, 6);
        System.out.println("Created a grid: " + grid);

        // Start the game
        grid.startGame();
    }

}

