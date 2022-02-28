package servPattern;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents each column
 *
 */
public class Column {


    private int id;
    private int height;
    private ArrayList<Checker> column;

    /**
     * Creates a column object
     * A column has a list of checkers
     * It is initialized with white checkers
     *
     * @param id: index of the column in the grid
     *        height: size of the column
     *
     */
    public Column(int id, int height) {

        this.id = id;
        this.height = height;
        this.column = new ArrayList<>(height);

        for (int i = 0; i < this.height; i++) {
            this.column.add( new Checker(i+1,"blank") );
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Checker> getColumn() {
        return column;
    }

    public void setColumn(ArrayList<Checker> column) {
        this.column = column;
    }

    /**
     * This method checks is the column if empty
     *
     * @return true if the column is empty
     */
    public boolean check_empty() {

        return this.column.isEmpty();
    }

    /**
     * This method checks is the column if full
     *
     * @return true if the column is full
     */
    public boolean check_full() {

        return !Objects.equals(this.getColumn().get(this.height - 1).getColor(), "blank");
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
