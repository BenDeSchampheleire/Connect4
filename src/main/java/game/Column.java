package game;

import java.util.ArrayList;
import java.util.Objects;

public class Column {

    private int id;
    private int height;
    private ArrayList<Checker> column;

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

    public boolean check_empty() {
        return this.column.isEmpty();
    }

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
