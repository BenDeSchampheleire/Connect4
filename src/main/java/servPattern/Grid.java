package servPattern;

import servPattern.Checker;
import servPattern.Column;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
/**
 * This class represents the grid
 *
 */
public class Grid {

    private int width;


    private int height;
    private ArrayList<Column> grid;

    /**
     * Creates a grid object
     * A grid has a list of columns
     *
     * @param width: number of columns
     *        height: size of the columns
     *
     */
    public Grid(int width, int height) {

        this.width = width;
        this.height = height;
        this.grid = new ArrayList<>(width);

        for (int i = 0; i < this.width; i++) {
            this.grid.add(new Column(i, this.height));
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Column> getGrid() {
        return grid;
    }

    public void setGrid(ArrayList<Column> grid) {
        this.grid = grid;
    }

    public void display_grid() {

        System.out.println("1 2 3 4 5 6 7");
        System.out.println("---------------");

        for (int row = this.height - 1; row >= 0; row--) {
            System.out.print("|");
            for (int col = 0; col < this.width; col++) {
                grid.get(col).getColumn().get(row).display_checker();
                System.out.print("|");
            }
            System.out.println("\n---------------");
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    /**
     * This method puts a checker of a given color in the specified column
     *
     * @param column_number: number of the column to put the checker in
     *        color: checker's color
     *
     */
    public void play_checker(int column_number, String color) {

        assertTrue(1 <= column_number && column_number <= this.width);

        Column column = this.getGrid().get(column_number - 1);

        for (int i = 0; i < this.height; i++) {
            Checker checker = column.getColumn().get(i);
            if (checker.getColor().equals("blank")) {
                checker.setColor(color);
                break;
            }
        }
    }

    /**
     * This method checks if the team of the given color has won
     *
     * @param color: color of the team
     *
     * @return true if the team has won
     */
    public boolean checkWin(String color) {

        // verticalCheck
        for (int j = 0; j < this.height - 3; j++) {
            for (int i = 0; i < this.width; i++) {
                if (grid.get(i).getColumn().get(j).getColor().equals(color)
                        && grid.get(i).getColumn().get(j + 1).getColor().equals(color)
                        && grid.get(i).getColumn().get(j + 2).getColor().equals(color)
                        && grid.get(i).getColumn().get(j + 3).getColor().equals(color)) {
                    return true;
                }
            }
        }

        // horizontalCheck
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width - 3; i++) {
                if (grid.get(i).getColumn().get(j).getColor().equals(color)
                        && grid.get(i + 1).getColumn().get(j).getColor().equals(color)
                        && grid.get(i + 2).getColumn().get(j).getColor().equals(color)
                        && grid.get(i + 3).getColumn().get(j).getColor().equals(color)) {
                    return true;
                }
            }
        }

        // \-diagonalCheck
        for (int i = 3; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (grid.get(i).getColumn().get(j).getColor().equals(color)
                        && grid.get(i - 1).getColumn().get(j + 1).getColor().equals(color)
                        && grid.get(i - 2).getColumn().get(j + 2).getColor().equals(color)
                        && grid.get(i - 3).getColumn().get(j + 3).getColor().equals(color)) {
                    return true;
                }
            }
        }

        // /-diagonalCheck
        for (int i = 3; i < this.width; i++) {
            for (int j = 3; j < this.height; j++) {
                if (grid.get(i).getColumn().get(j).getColor().equals(color)
                        && grid.get(i - 1).getColumn().get(j - 1).getColor().equals(color)
                        && grid.get(i - 2).getColumn().get(j - 2).getColor().equals(color)
                        && grid.get(i - 3).getColumn().get(j - 3).getColor().equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if the grid is full
     *
     * @return true is the grid is full
     */
    public boolean boardFull() {

        boolean full = true;
        for (int col = 0; col < this.width; col++) {
            if ( !this.getGrid().get(col).check_full() ) {
                full = false;
            }
        }
        return full;
    }

    /**
     * This method checks the state of the game
     *
     * @return the color of the winner's team
     */
    public String EndOfGame() {

        String winner;
        if ( checkWin("red") ) {
            winner = "red";
        } else if ( checkWin("yellow") ) {
            winner = "yellow";
        } else if ( boardFull() ) {
            winner = "nobody";
        } else {
            winner = "ongoing";
        }
        return winner;
    }
}
