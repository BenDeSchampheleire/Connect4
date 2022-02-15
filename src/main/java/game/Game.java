package game;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Grid grid = new Grid(7, 6);

        String color = "red";

        while (!grid.checkWin("red") && !grid.checkWin("yellow") ) {

            grid.display_grid();

            System.out.println("Select a column");

            if (color.equals("red")) {
                color = "yellow";
            }
            else if (color.equals("yellow")) {
                color = "red";
            }
            grid.play_checker(scanner.nextInt(),color);

        }

        if ( grid.checkWin("red") ) {
            System.out.println("red has won");
        }
        else if ( grid.checkWin("yellow") ) {
            System.out.println("yellow has won");
        }
    }
}
